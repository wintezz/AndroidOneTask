package com.example.androidonetask.data

import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> handleApi(
    execute: () -> Response<T>
): AppState<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            AppState.Success(body)
        } else {
            AppState.ServerError(code = response.code(), json = response.message())
        }
    } catch (e: HttpException) {
        AppState.ServerError(code = e.code(), "json = .message()")
    } catch (e: Throwable) {
        AppState.Error(Exception())
    }
}