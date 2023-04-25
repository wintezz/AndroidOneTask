package com.example.androidonetask.presentation.utils

import com.example.androidonetask.data.model.track.TrackListResponse
import com.example.androidonetask.data.model.track.TrackUiModel

object TrackMapper {

    fun buildFromTrack(response: TrackListResponse?): List<TrackUiModel> {
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


