package com.example.androidonetask.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/v3.0/tracks?client_id=f917c8e0")
    fun getTrackList():  Call<MutableList<TrackList>>

    companion object {
        /*const val CLIENT_ID = "f917c8e0"*/
        const val BASE_URL = "https://api.jamendo.com"
    }
}
