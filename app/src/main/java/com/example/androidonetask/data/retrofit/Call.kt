package com.example.androidonetask.data.retrofit

import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException

fun <T> Call<T>.handleApi(): AppState<T> {
    return try {
        val response = this.execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let { AppState.Success(it) } ?: AppState.Error(Throwable("Body is null"))
        } else {
            AppState.ServerError(
                code = response.code(),
                json = response.errorBody()?.string().orEmpty()
            )
        }
    } catch (e: HttpException) {
        AppState.Error(e)
    } catch (e: IOException) {
        AppState.Error(e)
    }
}