package com.example.androidonetask.data

import com.example.androidonetask.data.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.model.Track


object Repository {

    private val retrofitService: ApiService =
        RetrofitClient.getClient(BASE_URL)
            .create(ApiService::class.java)

    fun getTracks(): List<Track> {
        return try {
            retrofitService.getTrackList().execute().body()?.results ?: emptyList()
        } catch (e: Throwable) {
            emptyList()
        }
    }
}
