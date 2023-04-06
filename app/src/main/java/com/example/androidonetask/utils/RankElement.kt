package com.example.androidonetask.utils

class RankElement {

    companion object {

        const val KEY_FOR_ID = "KEY_FOR_ID"

        fun fillList(): List<String> {
            val data = mutableListOf<String>()
            (0..1000).forEach { i -> data.add("$i") }
            return data
        }
    }
}