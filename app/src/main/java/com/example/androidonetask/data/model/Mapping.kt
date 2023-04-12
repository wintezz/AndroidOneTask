package com.example.androidonetask.data.model

import com.example.androidonetask.data.Track

fun Track.toTUiModel() = TrackUiModel(
    name = name,
    duration = duration,
    artistName = artistName,
    albumImage = albumImage
)
