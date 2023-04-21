package com.example.androidonetask.presentation.fragment.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    open val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("Handle $throwable in CoroutineExceptionHandler")
        }

    open fun doWork(work: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            work()
        }
    }
}

sealed class BaseStateView {
    object Loading : BaseStateView()
}