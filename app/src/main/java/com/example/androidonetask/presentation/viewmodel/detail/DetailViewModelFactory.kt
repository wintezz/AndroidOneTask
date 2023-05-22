package com.example.androidonetask.presentation.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidonetask.data.repository.Repository
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}