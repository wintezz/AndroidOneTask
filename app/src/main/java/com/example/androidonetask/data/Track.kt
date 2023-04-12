package com.example.androidonetask.data

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("album_image")
    val albumImage: String
)

data class TrackListResponse(
    @SerializedName("results")
    val results: List<Track>?
)
