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
import com.example.androidonetask.databinding.FragmentBaseBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.fragment.base.BaseViewModel
import kotlinx.coroutines.launch

class WorkFragment :
    BaseFragment<BaseViewModel, FragmentBaseBinding>(FragmentBaseBinding::inflate) {

    private val viewModel: WorkViewModel by lazy {
        ViewModelProvider(
            this,
            WorkViewModelFactory(repository = RepositoryImpl())
        )[WorkViewModel::class.java]
    }

    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView
    )

    override fun getFragmentView() = R.layout.fragment_base

    override fun getViewModel() = BaseViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        initRecyclerView()
        clickViewError()
        setupObservers()
    }

    private fun showContent(tracks: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(tracks)
            progressBar.isVisible = false
            recView.isVisible = true
        }
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun clickViewError() {
        binding.imageRepeatRequest.setOnClickListener {
            viewModel.loadTracks()
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.staticState.collect { uiState ->
                when (uiState) {
                    /*      is TracksUiState.Loading -> showLoading()*/
                    is TracksUiState.Success -> showContent(uiState.tracks)
                    is TracksUiState.Error -> showError()
                }
            }
        }
    }
}

