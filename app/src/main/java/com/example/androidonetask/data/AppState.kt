package com.example.androidonetask.data

sealed class AppState<T> {
    data class Success<T>(val response: T?) : AppState<T>()
    data class Error<T>(val error: Exception) : AppState<T>()
    data class ServerError<T>(val code: Int, val json: String) : AppState<T>()
}
