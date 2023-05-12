package com.example.androidonetask.presentation.fragment.work

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.CardDelegate
import com.example.androidonetask.presentation.adapter.delegates.TitleDelegate
import com.example.androidonetask.presentation.adapter.delegates.TrackDelegate
import com.example.androidonetask.presentation.adapter.delegates.ViewPagerDelegate
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.VerticalItemDecorator
import com.example.androidonetask.presentation.utils.px
import com.example.androidonetask.presentation.viewmodel.work.MusicUiState
import com.example.androidonetask.presentation.viewmodel.work.WorkViewModel
import com.example.androidonetask.presentation.viewmodel.work.WorkViewModelFactory
import kotlinx.coroutines.launch

class WorkFragment :
    BaseFragment<WorkViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private val adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickNameHolder = ::onClickView
            ),
            CardDelegate(),
            ViewPagerDelegate(),
            TitleDelegate()
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist
    override fun getViewModelClass() = WorkViewModel::class.java
    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return WorkViewModelFactory(repository = RepositoryImpl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        initRecyclerView()
        setupObserverTrack()
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

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
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
}

