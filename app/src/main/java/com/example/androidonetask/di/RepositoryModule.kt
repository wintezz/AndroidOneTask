package com.example.androidonetask.di

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.data.retrofit.ApiService
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }
}