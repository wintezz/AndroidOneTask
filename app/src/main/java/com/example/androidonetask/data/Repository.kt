package com.example.androidonetask.data

import com.example.androidonetask.data.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.ApiService.Companion.CLIENT_ID


object Repository {

     val retrofitService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    fun getTracks()  {
        retrofitService.getTrackList(CLIENT_ID).execute().body()?.results
    }
}
