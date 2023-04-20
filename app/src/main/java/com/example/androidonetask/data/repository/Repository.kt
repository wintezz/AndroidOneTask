package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.TrackListResponse
import com.example.androidonetask.data.retrofit.AppState

interface Repository {
    suspend fun getTracks(): AppState<TrackListResponse>
}
