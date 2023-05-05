package com.example.androidonetask.presentation.model

sealed class Item {

    class TitleUiModel(
        val id: Int,
        val title: String
    ) : Item()

    data class PagerUiModel(
        val results: List<AlbumUiModel>
    ) : Item()

    data class AlbumUiModel(
        val image: String?,
        val name: String?,
        val artistName: String?
    ) : Item()

    data class TrackUiModel(
        val name: String?,
        val duration: String?,
        val artistName: String?,
        val albumImage: String?
    ) : Item()
}
