package com.example.androidonetask.presentation.fragment.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.utils.TrackMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WorkViewModel(private val repository: Repository) : ViewModel() {

    private val mutableState = MutableStateFlow(TracksUiState.Success(emptyList()))
    val staticState: StateFlow<TracksUiState> = mutableState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getTracks()) {
                is AppState.Success -> {
                    val data = response.data
                    val list = TrackMapper.buildFrom(data)
                    mutableState.value = TracksUiState.Success(list)
                }
                else -> TracksUiState.Error(exception = Throwable())
            }
        }
    }
}

sealed class TracksUiState {
    data class Success(val tracks: List<TrackUiModel>) : TracksUiState()
    data class Error(val exception: Throwable) : TracksUiState()
}

