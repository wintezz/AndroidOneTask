package com.example.androidonetask.presentation.viewmodel.bottom

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.example.androidonetask.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var exoPlayer: ExoPlayer? = null
    var playWhenReady = true
    var currentItem = 0
    var playbackPosition = 0L

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