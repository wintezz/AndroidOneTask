package com.example.androidonetask.data

import com.example.androidonetask.data.model.TrackListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/v3.0/tracks")
    fun getTrackList(): Call<TrackListResponse>

    companion object {
        const val CLIENT_ID = "f917c8e0"
        const val BASE_URL = "https://api.jamendo.com"
    }
}
