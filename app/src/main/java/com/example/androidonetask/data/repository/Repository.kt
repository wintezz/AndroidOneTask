package com.example.androidonetask.data.repository

import com.example.androidonetask.data.ApiService
import com.example.androidonetask.data.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.AppState
import com.example.androidonetask.data.RetrofitClient
import com.example.androidonetask.data.model.TrackListResponse
import retrofit2.HttpException
import java.io.IOException

object Repository {

    private val retrofitService: ApiService =
        RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

    fun getTracks(): AppState<TrackListResponse>? {
        return try {
            val track = retrofitService.getTrackList().execute().body()
            AppState.Success(response = track)
        } catch (e: HttpException) {
            AppState.Error(error = e)
        } catch (e: IOException) {
            AppState.Error(error = e)
        }
    }
}

