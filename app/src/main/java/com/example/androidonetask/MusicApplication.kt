package com.example.androidonetask

import android.app.Application
import com.example.androidonetask.di.ApplicationComponent
import com.example.androidonetask.di.DaggerApplicationComponent

class MusicApplication : Application() {

    private var component: ApplicationComponent? = null

    val applicationComponent: ApplicationComponent
        get() = checkNotNull(component)

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.create()
    }
}