package com.example.androidonetask.presentation.fragment.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.androidonetask.databinding.FragmentBottomSheetBinding
import com.example.androidonetask.presentation.viewmodel.bottom.BottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheet : BottomSheetDialogFragment() {

    private val viewModel: BottomSheetViewModel by viewModels()

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding
            .inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializationPlayer()
    }

    override fun onPause() {
        super.onPause()
        viewModel.releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        viewModel.releasePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initializationPlayer() {
        viewModel.exoPlayer = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->

                binding.playerView.player = exoPlayer

                val mediaItems = arguments?.getStringArrayList(AUDIO)?.map {
                    MediaItem.Builder()
                        .setUri(it)
                        .build()
                }

                if (mediaItems != null) {
                    exoPlayer.addMediaItems(mediaItems)
                }

                exoPlayer.playWhenReady = viewModel.playWhenReady
                exoPlayer.seekTo(viewModel.currentItem, viewModel.playbackPosition)
                exoPlayer.prepare()
            }
    }

    companion object {
        const val AUDIO = "audio"
    }
}