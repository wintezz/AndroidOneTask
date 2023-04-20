package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.TrackListResponse

interface Repository {
    suspend fun getTracks(): TrackListResponse
}
