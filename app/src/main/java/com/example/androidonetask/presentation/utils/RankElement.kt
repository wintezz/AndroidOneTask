package com.example.androidonetask.presentation.utils

import com.example.androidonetask.data.model.TrackUiModel

fun fillList(): List<TrackUiModel> {
    val data = mutableListOf<TrackUiModel>()
    (0..1000).forEach { i ->
        data.add(
            TrackUiModel(
                name = "$i",
                duration = "$i",
                artistName = "$i",
                albumImage = "$i"
            )
        )
    }
    return data
}

