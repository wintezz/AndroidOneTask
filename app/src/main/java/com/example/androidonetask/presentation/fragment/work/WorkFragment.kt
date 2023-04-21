package com.example.androidonetask.presentation.fragment.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import kotlinx.coroutines.launch

class WorkFragment : Fragment() {

    private lateinit var viewModel: WorkViewModel
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel()
        viewModel.init()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding
            .inflate(
                inflater, container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        initRecyclerView()
        clickViewError()
        setupObservers()
        showLoading()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showContent(tracks: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(tracks)
            progressBar.isVisible = false
            recView.isVisible = true
        }
    }

    private fun showError(exception: Throwable?) {
        with(binding) {
            progressBar.isVisible = false
            recView.isVisible = false
            textViewError.isVisible = true
            imageRepeatRequest.isVisible = true
        }
    }

    private fun showLoading() {
        with(binding) {
            textViewError.isVisible = false
            imageRepeatRequest.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun clickViewError() {
        binding.imageRepeatRequest.setOnClickListener {
            showLoading()
            viewModel.init()
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            WorkViewModelFactory(repository = RepositoryImpl())
        )[WorkViewModel::class.java]
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.staticState.collect { uiState ->
                when (uiState) {
                    is TracksUiState.Success -> showContent(uiState.tracks)
                    is TracksUiState.Error -> showError(uiState.exception)
                }
            }
        }
    }
}

