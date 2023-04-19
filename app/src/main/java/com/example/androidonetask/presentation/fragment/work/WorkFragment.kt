package com.example.androidonetask.presentation.fragment.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter

class WorkFragment : Fragment(), WorkContract.View {

    private var workPresenter: WorkPresenter? = null
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
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

        workPresenter = WorkPresenter(
            mainView = this,
            repository = RepositoryImpl()
        )

        workPresenter?.loadTracks()
        initRecyclerView()
        clickViewError()
    }

    override fun onDestroyView() {
        _binding = null
        workPresenter?.onDestroyView()
        super.onDestroyView()
    }

    override fun showContent(data: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(data)
            progressBar.isVisible = false
            recView.isVisible = true
        }
    }

    override fun showError() {
        with(binding) {
            progressBar.isVisible = false
            recView.isVisible = false
            textViewError.isVisible = true
            imageRepeatRequest.isVisible = true
        }
    }

    override fun showLoading() {
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
            workPresenter?.loadTracks()
            showLoading()
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }
}

