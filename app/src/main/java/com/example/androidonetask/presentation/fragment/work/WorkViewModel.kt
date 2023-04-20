package com.example.androidonetask.presentation.fragment.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.Resource
import kotlinx.coroutines.Dispatchers

class WorkViewModel(private val repository: Repository) : ViewModel() {

    fun loadData() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data = repository.getTracks()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}