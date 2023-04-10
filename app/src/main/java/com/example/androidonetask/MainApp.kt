package com.example.androidonetask

import android.app.Application
import com.example.androidonetask.data.MusicApiService
import com.example.androidonetask.data.MusicApiService.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApp : Application() {

    private lateinit var apiService: MusicApiService

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MusicApiService::class.java)

    }
}