package com.example.androidonetask.utils

import com.example.androidonetask.data.Track

fun fillList(): List<Track> {
    val data = mutableListOf<Track>()
    (0..1000).forEach { i ->
        data.add(
            Track(
                id = "$i",
                name = "$i",
                duration = "$i",
                artistName = "$i",
                albumImage = "$i"
            )
        )
    }
    return data
}

