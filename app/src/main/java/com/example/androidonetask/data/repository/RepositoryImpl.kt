package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import com.example.androidonetask.data.retrofit.ApiService
import com.example.androidonetask.data.retrofit.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.data.retrofit.RetrofitClient
import com.example.androidonetask.data.retrofit.handleApi

class RepositoryImpl : Repository {

    private val apiService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    override suspend fun getTracks(offset: Int, count: Int): AppState<TrackListResponse> {
        return apiService.getTrackList(offset, count).handleApi()
    }

    override suspend fun getAlbums(): AppState<AlbumListResponse> {
        return apiService.getAlbumList().handleApi()
    }
}



