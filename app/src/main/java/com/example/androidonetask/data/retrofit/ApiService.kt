package com.example.androidonetask.data.retrofit

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v3.0/tracks")
    fun getTrackList(
        @Query("offset") offset: Int,
        @Query("result_count") count: Int
    ): Call<TrackListResponse>

    @GET("/v3.0/albums")
    fun getAlbumList(): Call<AlbumListResponse>

    companion object {
        const val CLIENT_ID = "f917c8e0"
        const val BASE_URL = "https://api.jamendo.com"
        const val OFF_SET = 0
        const val COUNT = 10
    }
}