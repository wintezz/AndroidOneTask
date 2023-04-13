package com.example.androidonetask.data.model

object TrackMapper {

    fun buildFrom(response: Track): TrackUiModel {
        return TrackUiModel(
            name = response.name,
            duration = response.duration,
            artistName = response.artistName,
            albumImage = response.albumImage
        )
    }
}


