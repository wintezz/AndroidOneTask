package com.example.androidonetask.presentation.viewmodel.work

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.ApiService.Companion.COUNT
import com.example.androidonetask.data.retrofit.ApiService.Companion.OFF_SET
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.TrackMapper
import com.example.androidonetask.presentation.viewmodel.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.atomic.AtomicBoolean

class WorkViewModel(private val repository: Repository) : BaseViewModel() {

    private val mutableStateMusic =
        MutableStateFlow<MusicUiState>(MusicUiState.Success(emptyList()))

    val staticStateMusic: StateFlow<MusicUiState> = mutableStateMusic

    var isLastPageLoaded: Boolean = false
    var isLoadingTracks = AtomicBoolean(false)

    private var offSet = OFF_SET
    private var list: MutableList<Item> = mutableListOf()

    init {
        loadMusic()
    }

    fun nextLoadPage() {
        offSet += COUNT
        paginationTracks()
        isLoadingTracks.getAndSet(true)
    }

    private fun paginationTracks() {
        isLoadingTracks.getAndSet(false)
        doWork {
            val tracks = repository.getTracks(offSet, COUNT)

            if (tracks is AppState.Success) {
                list.removeLast()
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
}

