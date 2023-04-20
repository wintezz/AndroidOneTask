package com.example.androidonetask.presentation.fragment.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.data.retrofit.Status
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.presentation.utils.TrackMapper

class WorkFragment : Fragment() {

    private lateinit var viewModel: WorkViewModel
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView
    )

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
        setupViewModel()
        setupObservers()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showContent(data: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(data)
            progressBar.isVisible = false
            recView.isVisible = true
        }
    }

    private fun showError() {
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
            setupObservers()
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
        showLoading()
        viewModel.loadData().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val data = resource.data
                        val list = TrackMapper.buildFrom(data)
                        resource.data?.let { showContent(list) }
                    }
                    else -> showError()
                }
            }
        }
    }
}

