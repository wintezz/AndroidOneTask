package com.example.androidonetask.presentation.fragment.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import com.example.androidonetask.databinding.FragmentBottomSheetBinding
import com.example.androidonetask.presentation.viewmodel.bottom.BottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheet : BottomSheetDialogFragment() {

    private val viewModel: BottomSheetViewModel by viewModels()

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding
        get() = _binding ?: throw Throwable("BottomSheet binding is not initialized")

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

        attachViewAndSetDataExoPlayer()
    }

    override fun onStop() {
        super.onStop()
        viewModel.releasePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachViewAndSetDataExoPlayer() {
        viewModel.initializationPlayer(requireContext())
        binding.playerView.player = viewModel.exoPlayer

        val mediaItem = arguments?.getString(AUDIO).toString()
        val getMedia = MediaItem.fromUri(mediaItem)
        viewModel.exoPlayer?.addMediaItem(getMedia)
    }

    companion object {
        const val AUDIO = "audio"
    }
}