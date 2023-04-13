package com.example.androidonetask.data

import com.example.androidonetask.data.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.ApiService.Companion.CLIENT_ID
import com.example.androidonetask.data.model.Track


object Repository {

    private val retrofitService: ApiService =
        RetrofitClient.getClient(BASE_URL)
            .create(ApiService::class.java)

    fun getTracks(): List<Track> {
        return retrofitService.getTrackList(CLIENT_ID).execute().body()?.results ?: emptyList()
    }
}
