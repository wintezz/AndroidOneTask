package com.example.androidonetask

import android.app.Application
import com.example.androidonetask.utils.ActivityLifecycleHandler
import com.example.androidonetask.utils.ActivityLifecycleListener

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(ActivityLifecycleListener)

        registerActivityLifecycleCallbacks(ActivityLifecycleHandler(this))
    }
}
