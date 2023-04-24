package com.example.androidonetask.data.model.album

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("releasedate")
    val releaseDate: String? = null,
    @SerializedName("artist_id")
    val artistId: String? = null,
    @SerializedName("artist_name")
    val artistName: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("zip")
    val zip: String? = null,
    @SerializedName("shorturl")
    val shortUrl: String? = null,
    @SerializedName("shareurl")
    val shareUrl: String? = null,
    @SerializedName("zip_allowed")
    val zipAllowed: Boolean? = null
)

data class AlbumListResponse(
    @SerializedName("results")
    val results: List<Album>?
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

