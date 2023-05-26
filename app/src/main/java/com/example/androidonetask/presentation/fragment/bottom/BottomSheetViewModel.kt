package com.example.androidonetask.presentation.fragment.bottom

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    var exoPlayer: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    init {
        initializationPlayer(context)
    }

    private fun initializationPlayer(context: Context) {
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