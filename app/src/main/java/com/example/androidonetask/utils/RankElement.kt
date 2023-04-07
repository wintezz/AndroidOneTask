package com.example.androidonetask.utils

class RankElement {
    companion object {

        fun fillList(): List<String> {
            val data = mutableListOf<String>()
            (0..1000).forEach { i -> data.add("$i") }
            return data
        }
    }
}