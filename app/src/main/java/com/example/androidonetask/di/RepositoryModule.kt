package com.example.androidonetask.di

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }
}