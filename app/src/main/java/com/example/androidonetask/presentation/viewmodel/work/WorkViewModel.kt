package com.example.androidonetask.presentation.viewmodel.work

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.TrackMapper
import com.example.androidonetask.presentation.viewmodel.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WorkViewModel(private val repository: Repository) : BaseViewModel() {

    private val mutableStateMusic =
        MutableStateFlow<MusicUiState>(MusicUiState.Success(emptyList()))

    val staticStateMusic: StateFlow<MusicUiState> = mutableStateMusic

    init {
        loadMusic()
    }

    private fun loadMusic() {
        doWork {
            val responseTrack = async { repository.getTracks() }
            val responseAlbum = async { repository.getAlbums() }

            val tracks = responseTrack.await()
            val albums = responseAlbum.await()

            if (tracks is AppState.Success && albums is AppState.Success) {
                mutableStateMusic.value = MusicUiState.Success(
                    TrackMapper.toUiState(
                        albumListResponse = albums.data,
                        trackListResponse = tracks.data
                    )
                )
            } else {
                _error.postValue(true)
            }
        }
    }
}

sealed class MusicUiState {
    data class Success(val music: List<Item>) : MusicUiState()
}

