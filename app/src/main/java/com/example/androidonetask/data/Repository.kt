package com.example.androidonetask.data

import com.example.androidonetask.data.ApiService.Companion.BASE_URL


object Repository {

    val retrofitService: ApiService
        get() = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
}
