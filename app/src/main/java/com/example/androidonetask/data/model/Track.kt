package com.example.androidonetask.data

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @SerializedName("artist_name")
    val artistName: String? = null,
    @SerializedName("album_image")
    val albumImage: String? = null,
    @SerializedName("album_name")
    val albumName: String? = null,
    @SerializedName("releasedate")
    val releaseDate: String? = null,
    @SerializedName("audio")
    val audio: String? = null,
    @SerializedName("audiodownload")
    val audioDownload: String? = null
)

data class TrackListResponse(
    @SerializedName("results")
    val results: List<Track>?
)
