package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.TrackListResponse
import com.example.androidonetask.data.retrofit.ApiService
import com.example.androidonetask.data.retrofit.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.retrofit.RetrofitClient
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RepositoryImpl : Repository {

    private val apiService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    override fun getTracks(): Observable<TrackListResponse> {
        return apiService.getTrackList()
            .subscribeOn(Schedulers.io())
    }
}