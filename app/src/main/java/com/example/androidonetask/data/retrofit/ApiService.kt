package com.example.androidonetask.data.retrofit

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/v3.0/tracks")
    fun getTrackList(): Call<TrackListResponse>

    @GET("/v3.0/albums")
    fun getAlbumList(): Call<AlbumListResponse>

    companion object {
        const val CLIENT_ID = "f917c8e0"
        const val BASE_URL = "https://api.jamendo.com"
    }
}