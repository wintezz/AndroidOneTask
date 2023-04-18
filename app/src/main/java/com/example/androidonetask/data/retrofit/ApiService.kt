package com.example.androidonetask.data.retrofit

import com.example.androidonetask.data.model.TrackListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/v3.0/tracks")
    fun getTrackList(): Single<TrackListResponse>

    companion object {
        const val CLIENT_ID = "f917c8e0"
        const val BASE_URL = "https://api.jamendo.com"
    }
}
