package com.example.androidonetask.data.repository

import com.example.androidonetask.data.retrofit.ApiService
import com.example.androidonetask.data.retrofit.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.retrofit.RetrofitClient
import com.example.androidonetask.data.retrofit.handleApi

class RepositoryImpl : Repository {

    private val apiService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    override suspend fun getTracks() =
        apiService.getTrackList().handleApi()
}



