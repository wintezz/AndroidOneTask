package com.example.androidonetask.presentation.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.androidonetask.R
import com.example.androidonetask.presentation.activity.MainActivity

class MusicService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("music", "MusicService onCreate")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        val notification: Notification = Notification.Builder(this, MUSIC_SERVICE)
            .setContentTitle(getText(R.string.works))
            .setContentText(getText(PLAY.toInt()))
            .setSmallIcon(R.drawable.play_button)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(ID, notification)

        return START_STICKY
    }

    companion object {
        const val ID = 12
        const val MUSIC_SERVICE = "Service"
        const val CHANNEL_ID = "Chanel"
        const val PLAY = "Now playing song"
        const val DESCRIPTION = "This is a channel for showing song"
    }
}