package com.example.androidonetask.presentation.fragment.work

import com.example.androidonetask.data.model.track.TrackUiModel
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.fragment.base.BaseViewModel
import com.example.androidonetask.presentation.utils.TrackMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WorkViewModel(private val repository: Repository) : BaseViewModel() {

    private val mutableState =
        MutableStateFlow<TracksUiState>(TracksUiState.Success(emptyList()))

    val staticState: StateFlow<TracksUiState> = mutableState

    init {
        loadTracks()
    }

    private fun loadTracks() {
        doWork {
            when (val response = repository.getTracks()) {
                is AppState.Success -> {
                    val list = TrackMapper.buildFromTrack(response.data)
                    mutableState.value = TracksUiState.Success(list)
                }
                else -> _error.postValue(true)
            }
        }
    }
}

sealed class TracksUiState {
    data class Success(val tracks: List<TrackUiModel>) : TracksUiState()
}
