package com.example.androidonetask.presentation.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.androidonetask.R
import com.example.androidonetask.presentation.fragment.work.WorkFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicService : Service() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show()

        showNotification()

        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder {
        return MusicBinder()
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("RemoteViewLayout")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification() {

        val notificationIntent = Intent(this, WorkFragment::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setContentTitle("Foreground Service")
            .setContentText("Hello World")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .addAction(R.drawable.play_repeat, "Repeat", null)
            .addAction(R.drawable.play_button, "Play", null)
            .addAction(R.drawable.play_repeat, "Next", null)
            .build()

        startForeground(ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    inner class MusicBinder : Binder() {
        fun currentService(): MusicService {
            return this@MusicService
        }
    }

    companion object {
        const val ID = 1
        const val CHANNEL_ID = "ForegroundChannel"
    }
}
