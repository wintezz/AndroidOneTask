package com.example.androidonetask.presentation.utils

import com.example.androidonetask.data.model.TrackListResponse
import com.example.androidonetask.data.model.TrackUiModel

object TrackMapper {

    fun buildFrom(response: TrackListResponse?): List<TrackUiModel> {
        return response?.results?.map {
            TrackUiModel(
                name = it.name,
                artistName = it.artistName,
                duration = it.duration,
                albumImage = it.albumImage
            )
        } ?: emptyList()
    }
}


