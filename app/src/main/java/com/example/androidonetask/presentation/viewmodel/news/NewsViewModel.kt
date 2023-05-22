package com.example.androidonetask.presentation.viewmodel.news

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: Repository) : BaseViewModel()