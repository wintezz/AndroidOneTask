package com.example.androidonetask.data.repository

import com.example.androidonetask.data.ApiService
import com.example.androidonetask.data.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.AppState
import com.example.androidonetask.data.RetrofitClient
import com.example.androidonetask.data.handleApi
import com.example.androidonetask.data.model.TrackListResponse

object Repository {

    private val retrofitService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    fun getTracks(): AppState<TrackListResponse> = retrofitService.getTrackList().handleApi()
}
