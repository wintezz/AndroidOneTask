package com.example.androidonetask.data

import com.example.androidonetask.data.ApiService.Companion.CLIENT_ID
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getClient(baseUrl: String): Retrofit {

        fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
            it.request()
                .newBuilder()
                .url(
                    it.request().url.newBuilder().addQueryParameter("client_id", CLIENT_ID).build()
                )
                .build()
        )

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { apiKeyAsHeader(it) }
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

