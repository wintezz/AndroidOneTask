package com.example.androidonetask.data

sealed class AppState<T>(
    data: T? = null,
    exception: Exception? = null
) {
    data class Success<T>(val data: T?) : AppState<T>(data, null)
    data class Error<T>(val exception: Exception) : AppState<T>(null, exception)
    data class ServerError<T>(val code: Int, val json: String) : AppState<T>()
}
