package com.example.androidonetask.presentation.viewmodel.work

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.ApiService.Companion.COUNT
import com.example.androidonetask.data.retrofit.ApiService.Companion.OFF_SET
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.TrackMapper
import com.example.androidonetask.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext context: Context
) : BaseViewModel() {

    private val mutableStateMusic =
        MutableStateFlow<MusicUiState>(MusicUiState.Success(emptyList()))
    val staticStateMusic: StateFlow<MusicUiState> = mutableStateMusic

    var isLastPageLoaded: Boolean = false
    var isLoadingTracks = AtomicBoolean(false)
    var shouldUpdateSeekbar = true

    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context)
        .build()
        .also { exoPlayer ->
            exoPlayer.prepare()
        }

    private var offSet = OFF_SET

    private var list: MutableList<Item> = mutableListOf()

    init {
        loadMusic()
        initListenerExoPlayer()
    }

    override fun onCleared() {
        super.onCleared()

        exoPlayer.release()
    }

    fun onStart() {
        exoPlayer.prepare()
    }

    fun onStop() {
        exoPlayer.stop()
    }

    fun onStartAndStopCallBacksExoPlayer() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun nextLoadPage() {
        offSet += COUNT
        paginationTracks()
        isLoadingTracks.getAndSet(true)
    }

    fun onItemClickAudioUrl(
        audio: String,
        artistName: String,
        albumImage: String,
        trackName: String,
        isPlaying: Boolean
    ) {
        mutableStateMusic.value =
            MusicUiState.DataExoPlayer(artistName, albumImage, trackName)

        list.forEachIndexed { _, item ->
            if ((item is Item.TrackUiModel)) {
                item.isPlaying = item.audio == audio && !isPlaying
            }
        }

        list.filterIsInstance<Item.TrackUiModel>()
            .indexOfFirst { audio == it.audio }
            .takeIf { it >= 0 }?.let { index ->
                exoPlayer.seekToDefaultPosition(index)
            }
        exoPlayer.play()
    }

    private fun initListenerExoPlayer() {
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) {
                    getPlayerSeekElement()
                }
            }
        })
    }

    private fun onExoPlayerSeekIncrement(currentTime: Long) {
        mutableStateMusic.value = MusicUiState.TimeSeekBar(currentTime)
    }

    private fun getPlayerSeekElement() {
        viewModelScope.launch {
            onExoPlayerSeekIncrement(exoPlayer.currentPosition)
            delay(1_000)
            if (exoPlayer.isPlaying) {
                getPlayerSeekElement()
            }
        }
    }

    private fun paginationTracks() {
        isLoadingTracks.getAndSet(false)
        doWork {
            val tracks = repository.getTracks(offSet, COUNT)

            if (tracks is AppState.Success) {
                list.removeLast()
                withContext(Dispatchers.Main) {
                    tracks.data?.results?.mapNotNull { it.audio?.let { it1 -> MediaItem.fromUri(it1) } }
                        ?.let(exoPlayer::addMediaItems)
                }
                list.addAll(TrackMapper.buildFromTrack(response = tracks.data))
                list.add(Item.LoaderUiModel(false))

                isLastPageLoaded = tracks.data?.headers?.next == null

                mutableStateMusic.value = MusicUiState.Success(list)
            } else {
                list.removeLast()
                list.add(Item.ErrorUiModel("Error…Repeat later again"))
            }
        }
    }

    private fun loadMusic() {
        doWork {
            val responseTrack = async { repository.getTracks(offSet, COUNT) }
            val responseAlbum = async { repository.getAlbums() }

            val tracks = responseTrack.await()
            val albums = responseAlbum.await()

            if (tracks is AppState.Success && albums is AppState.Success) {
                withContext(Dispatchers.Main) {
                    tracks.data?.results?.mapNotNull { it.audio?.let { it1 -> MediaItem.fromUri(it1) } }
                        ?.let(exoPlayer::addMediaItems)
                }
                list.addAll(
                    TrackMapper.toUiState(
                        trackListResponse = tracks.data,
                        albumListResponse = albums.data
                    )
                )
                mutableStateMusic.value = MusicUiState.Success(list)
            } else {
                _error.postValue(true)
            }
        }
    }
}

sealed class MusicUiState {
    data class Success(val music: List<Item>) : MusicUiState()

    data class TimeSeekBar(val time: Long) : MusicUiState()

    data class DataExoPlayer(
        val albumImage: String,
        val artistName: String,
        val trackName: String
    ) : MusicUiState()
}