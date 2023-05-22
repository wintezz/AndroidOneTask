package com.example.androidonetask.di

import com.example.androidonetask.data.retrofit.ApiService
import com.example.androidonetask.data.retrofit.ApiService.Companion.BASE_URL
import com.example.androidonetask.data.retrofit.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor::apiKeyAsHeader)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}