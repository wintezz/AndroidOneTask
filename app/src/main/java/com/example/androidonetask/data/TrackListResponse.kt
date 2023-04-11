package com.example.androidonetask.data

data class TrackListResponse(
   val status: String,
   val code: Int,
   val error_message: String,
   val warnings: String,
   val results_count: Int,
   val next: String,
   val results: List<TrackList>
)
