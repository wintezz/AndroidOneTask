package com.example.androidonetask.presentation.viewmodel.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidonetask.data.repository.Repository
import javax.inject.Inject

class InfoViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InfoViewModel(repository) as T
    }
}