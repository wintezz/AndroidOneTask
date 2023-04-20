package com.example.androidonetask.data.retrofit

import com.example.androidonetask.data.model.TrackListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/v3.0/tracks")
    suspend fun getTrackList(): TrackListResponse

    companion object {
        const val CLIENT_ID = "f917c8e0"
        const val BASE_URL = "https://api.jamendo.com"
    }
}
