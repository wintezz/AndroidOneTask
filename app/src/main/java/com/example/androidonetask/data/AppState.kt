package com.example.androidonetask.data

import com.example.androidonetask.data.model.TrackListResponse

sealed class AppState {

    data class Success(val response: TrackListResponse) : AppState()
    data class ServerError(val code: Int, val json: String) : AppState()
    data class Error(val error: Throwable) : AppState()
}
