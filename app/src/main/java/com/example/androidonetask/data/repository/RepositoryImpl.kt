package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.TrackListResponse
import com.example.androidonetask.data.retrofit.ApiService
import com.example.androidonetask.data.retrofit.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.data.retrofit.RetrofitClient
import com.example.androidonetask.data.retrofit.request
import io.reactivex.Observable

class RepositoryImpl : Repository {

    private val apiService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    override fun getTracks(): Observable<AppState<TrackListResponse>>{
        return apiService.getTrackList().request()
    }
}