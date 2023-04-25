package com.example.androidonetask.presentation.fragment.work

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.model.track.TrackUiModel
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import kotlinx.coroutines.launch

class WorkFragment :
    BaseFragment<WorkViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView
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
        setupObservers()
    }

    private fun showContent(tracks: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(tracks)
            recView.isVisible = true
        }
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.staticState.collect { uiState ->
                when (uiState) {
                    is TracksUiState.Success -> showContent(uiState.tracks)
                    is TracksUiState.Error -> {
                        showError()
                        binding.recView.isVisible = false
                    }
                }
            }
        }
    }
}

