package com.example.androidonetask.di

import android.content.Context
import com.example.androidonetask.MusicApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private val application = MusicApplication()

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application
}