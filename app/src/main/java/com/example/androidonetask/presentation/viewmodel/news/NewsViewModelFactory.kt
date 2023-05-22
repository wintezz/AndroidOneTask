package com.example.androidonetask.presentation.viewmodel.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidonetask.data.repository.Repository
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}