package com.example.androidonetask.data.retrofit

import okhttp3.Interceptor

object AuthInterceptor {

    fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder().url(
                it.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("client_id", ApiService.CLIENT_ID)
                    .build()
            )
            .build()
    )
}