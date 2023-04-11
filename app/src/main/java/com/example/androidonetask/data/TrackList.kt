package com.example.androidonetask.data

import com.google.gson.annotations.SerializedName

data class TrackList(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("artist_name")
    val artist_name: String,
    @SerializedName("album_image")
    val album_image: String


)
