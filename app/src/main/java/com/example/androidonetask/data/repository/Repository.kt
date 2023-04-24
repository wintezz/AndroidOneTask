package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import com.example.androidonetask.data.retrofit.AppState

interface Repository {
    suspend fun getTracks(): AppState<TrackListResponse>

    suspend fun getAlbums(): AppState<AlbumListResponse>
}