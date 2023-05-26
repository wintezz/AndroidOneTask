package com.example.androidonetask.presentation.viewmodel.bottom

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    context: Context
) : ViewModel() {

    var exoPlayer: ExoPlayer? = null
    var playWhenReady = true
    var currentItem = 0
    var playbackPosition = 0L

    fun initializationPlayer(context: Context) {
        exoPlayer = ExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }
    }

    fun releasePlayer() {
        exoPlayer?.let { exoPlayer ->
            playWhenReady = exoPlayer.playWhenReady
            currentItem = exoPlayer.currentMediaItemIndex
            playbackPosition = exoPlayer.currentPosition
            exoPlayer.release()
        }
        exoPlayer = null
    }
}