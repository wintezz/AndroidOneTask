package com.example.androidonetask.presentation.viewmodel.artist

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val repository: Repository) : BaseViewModel()