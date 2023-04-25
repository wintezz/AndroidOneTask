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

    val _error: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> = _error

    var lastWork: (suspend CoroutineScope.() -> Unit)? = null

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("Handle $throwable in CoroutineExceptionHandler")
            _progress.postValue(false)
            _error.postValue(true)
        }

    open fun onButtonRetryClick() {
        lastWork?.let(::doWork)
        _error.postValue(false)
    }

    fun doWork(work: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            lastWork = work
            _progress.postValue(true)
            work()
            _progress.postValue(false)
        }
    }
}