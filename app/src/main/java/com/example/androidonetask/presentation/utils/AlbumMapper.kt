package com.example.androidonetask.presentation.utils

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.album.AlbumUiModel

object AlbumMapper {

    fun buildFromAlbum(response: AlbumListResponse?): List<AlbumUiModel> {
        return response?.results?.map {
            AlbumUiModel(
                name = it.name,
                artistName = it.artistName,
                image = it.image
            )
        } ?: emptyList()
    }
}