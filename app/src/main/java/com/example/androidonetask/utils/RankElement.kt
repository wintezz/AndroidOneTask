package com.example.androidonetask.utils

import com.example.androidonetask.data.TrackList

class RankElement {
    companion object {

        fun fillList(): List<TrackList> {
            val data = mutableListOf<TrackList>()
            (0..1000).forEach { i ->
                data.add(
                    TrackList(
                        id = "$i",
                        name = "$i",
                        duration = "$i",
                        artist_name = "$i",
                        album_image = "$i"
                    )
                )
            }
            return data
        }
    }
}