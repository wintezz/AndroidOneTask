package com.example.androidonetask.data

import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException

fun <T> Call<T>.handleApi(): AppState<T> {
    return try {
        val response = this.execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            AppState.Success(body)
        } else {
            AppState.ServerError(code = response.code(), json = response.message())
        }
    } catch (e: HttpException) {
        AppState.Error(e)
    } catch (e: IOException) {
        AppState.Error(e)
    }
}