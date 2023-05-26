package com.example.androidonetask.presentation.fragment.work

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.*
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.PaginationScrollListener
import com.example.androidonetask.presentation.utils.VerticalItemDecorator
import com.example.androidonetask.presentation.utils.px
import com.example.androidonetask.presentation.viewmodel.work.MusicUiState
import com.example.androidonetask.presentation.viewmodel.work.WorkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WorkFragment :
    BaseFragment<WorkViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private val adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickNameHolder = ::onClickView,
                onItemClickAudioUrl = ::attachViewAndSetDataExoPlayer
            ),
            CardDelegate(),
            ViewPagerDelegate(),
            TitleDelegate(),
            LoaderDelegate(),
            ErrorDelegate(
                onItemClickViewHolder = ::onClickError
            )
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist

    override fun getViewModelClass() = WorkViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        initRecyclerView()
        setupObserverTrack()
        scrollRecyclerView()
    }

    override fun onStop() {
        super.onStop()
        viewModel.releasePlayer()
    }

    private fun showContent(music: List<Item>) {
        with(binding) {
            adapter.updateItem(music)
            recView.isVisible = true
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        requireContext().getDrawable(R.drawable.divider_drawable)
            ?.let { VerticalItemDecorator(it, 32.px, listOf(R.layout.track_element_list)) }?.let {
                binding.recView.addItemDecoration(it)
            }
    }

    private fun scrollRecyclerView() {
        binding.recView.addOnScrollListener(object :
            PaginationScrollListener(binding.recView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.nextLoadPage()
            }

            override fun isLastPage() = viewModel.isLastPageLoaded
            override fun isLoading() = viewModel.isLoadingTracks
        })
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    private fun onClickError() {
        viewModel.nextLoadPage()
    }

    private fun setupObserverTrack() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.staticStateMusic.collect { trackState ->
                when (trackState) {
                    is MusicUiState.Success -> showContent(trackState.music)
                }
            }
        }
    }

    private fun attachViewAndSetDataExoPlayer(audio: String) {
        binding.bottomLayout.playerView.player = viewModel.exoPlayer
        val getMedia = MediaItem.fromUri(audio)
        viewModel.exoPlayer?.addMediaItem(getMedia)
    }
}