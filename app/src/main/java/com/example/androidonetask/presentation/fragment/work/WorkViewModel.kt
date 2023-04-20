package com.example.androidonetask.presentation.fragment.work

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.utils.TrackMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkViewModel(private val repository: Repository) : ViewModel() {

    val trackList = MutableLiveData<List<TrackUiModel>>()

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getTracks()) {
                is AppState.Success -> {
                    val data = response.data
                    val list = TrackMapper.buildFrom(data)

                    trackList.postValue(list)
                }
                else -> Unit
            }
        }
    }
}
