package com.example.androidonetask.data

import retrofit2.Retrofit

class RetrofitFactory {

    companion object {
        fun getRetroInstance(): Retrofit {
            val baseUrl = "https://api.jamendo.com"

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()

        }
    }
}