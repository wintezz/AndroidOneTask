package com.example.androidonetask.presentation.fragment.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null

    private val binding get() = _binding!!

    private var exoPlayer: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initializationPlayer()
    }

    override fun onResume() {
        super.onResume()
        if (exoPlayer == null) {
            initializationPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun releasePlayer() {
        exoPlayer?.let { exoPlayer ->
            playWhenReady = exoPlayer.playWhenReady
            currentItem = exoPlayer.currentMediaItemIndex
            playbackPosition = exoPlayer.currentPosition
            exoPlayer.release()
        }
        exoPlayer = null
    }

    private fun initializationPlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->

                binding.playerView.player = exoPlayer

                val mediaItem = MediaItem.fromUri(getString(R.string.mp3_song_1))
                exoPlayer.setMediaItem(mediaItem)

                val secondMediaItem = MediaItem.fromUri(getString(R.string.mp3_song_2))
                exoPlayer.addMediaItem(secondMediaItem)

                val underMediaItem = MediaItem.fromUri(getString(R.string.mp3_song_3))
                exoPlayer.addMediaItem(underMediaItem)

                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}