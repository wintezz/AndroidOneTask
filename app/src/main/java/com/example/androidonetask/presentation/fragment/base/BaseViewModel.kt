package com.example.androidonetask.presentation.fragment.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _progress: MutableLiveData<Boolean> = MutableLiveData()
    val progress: LiveData<Boolean> = _progress

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("Handle $throwable in CoroutineExceptionHandler")
        }

    fun doWork(work: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _progress.postValue(true)
            work()
            _progress.postValue(false)
        }
    }
}