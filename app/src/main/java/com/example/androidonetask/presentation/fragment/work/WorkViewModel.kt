package com.example.androidonetask.presentation.fragment.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.utils.TrackMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WorkViewModel(private val repository: Repository) : ViewModel() {

    private val mutableState =
        MutableStateFlow<TracksUiState>(TracksUiState.Loading)

    val staticState: StateFlow<TracksUiState> = mutableState

    init {
        loadTracks()
    }

    fun loadTracks() {
        viewModelScope.launch {
            mutableState.value = TracksUiState.Loading
            when (val response = repository.getTracks()) {
                is AppState.Success -> {
                    val list = TrackMapper.buildFrom(response.data)
                    mutableState.value = TracksUiState.Success(list)
                }
                is AppState.Error -> {
                    mutableState.value = TracksUiState.Error(response.exception)
                }
                else -> {}
            }
        }
    }
}

sealed class TracksUiState {
    object Loading : TracksUiState()
    data class Success(val tracks: List<TrackUiModel>) : TracksUiState()
    data class Error(val exception: Throwable? = null) : TracksUiState()
}
