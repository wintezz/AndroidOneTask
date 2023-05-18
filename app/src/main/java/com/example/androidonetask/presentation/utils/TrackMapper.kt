package com.example.androidonetask.presentation.utils

import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import com.example.androidonetask.presentation.model.Item

object TrackMapper {

    fun toUiState(
        trackListResponse: TrackListResponse?,
        albumListResponse: AlbumListResponse?
    ): List<Item> {
        val listTrack = buildFromTrack(trackListResponse)
        val listPager = buildFromPager(albumListResponse)
        val musicItemList = mutableListOf<Item>()
        musicItemList.add(Item.TitleUiModel(1, "Music"))
        listTrack.getOrNull(0)?.let { musicItemList.add(it) }
        listTrack.getOrNull(1)?.let { musicItemList.add(it) }
        musicItemList.add(Item.TitleUiModel(2, "Albums"))
        musicItemList.add(listPager)
        musicItemList.add(Item.TitleUiModel(3, "Music"))
        musicItemList.addAll(listTrack)
        musicItemList.add(Item.LoaderUiModel(true))

        return musicItemList
    }

    fun buildFromTrack(response: TrackListResponse?): List<Item.TrackUiModel> {
        return response?.results?.map {
            Item.TrackUiModel(
                name = it.name,
                artistName = it.artistName,
                duration = it.duration,
                albumImage = it.albumImage,
            )
        } ?: emptyList()
    }

    private fun buildFromPager(response: AlbumListResponse?): Item.PagerUiModel {
        return Item.PagerUiModel(
            response?.results?.map {
                Item.AlbumUiModel(
                    name = it.name,
                    artistName = it.artistName,
                    image = it.image
                )
            } ?: emptyList())
    }
}