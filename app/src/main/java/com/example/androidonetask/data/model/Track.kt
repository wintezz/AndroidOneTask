package com.example.androidonetask.data.model

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

data class TrackSettings(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("error_message")
    val errorMessage: String? = null,
    @SerializedName("warnings")
    val warnings: String? = null,
    @SerializedName("results_count")
    val resultsCount: Int? = null,
    @SerializedName("next")
    val next: String? = null
)