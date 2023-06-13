package com.example.androidonetask.presentation.utils

import com.example.androidonetask.presentation.model.Item

fun fillList(): List<Item.TrackUiModel> {
    val data = mutableListOf<Item.TrackUiModel>()
    (0..1000).forEach { i ->
        data.add(
            Item.TrackUiModel(
                name = "$i",
                duration = "$i",
                artistName = "$i",
                albumImage = "$i",
                audio = "$i",
                isPlaying = true
            )
        )
    }
    return data
}

