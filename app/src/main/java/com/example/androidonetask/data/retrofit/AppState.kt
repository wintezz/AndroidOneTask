package com.example.androidonetask.data.retrofit

/*
sealed class AppState<T> {
    data class Success<T>(val data: T?) : AppState<T>()
    data class ServerError<T>(val code: Int, val json: String) : AppState<T>()
    data class Error<T>(val exception: Throwable) : AppState<T>()
}*/

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)
    }
}