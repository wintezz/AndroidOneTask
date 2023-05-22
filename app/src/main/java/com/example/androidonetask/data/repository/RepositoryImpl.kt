package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import com.example.androidonetask.data.retrofit.ApiService
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.data.retrofit.handleApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getTracks(offset: Int, count: Int): AppState<TrackListResponse> {
        return apiService.getTrackList(offset, count).handleApi()
    }

    override suspend fun getAlbums(): AppState<AlbumListResponse> {
        return apiService.getAlbumList().handleApi()
    }
}