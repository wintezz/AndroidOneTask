package com.example.androidonetask.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MusicApiService {
    @Headers("?client_id=: $CLIENT_ID")
    @GET("/v3.0/tracks")
    fun getTrackList(): Call<MutableList<Track>>

    companion object {
        const val CLIENT_ID = "f917c8e0"
    }
}