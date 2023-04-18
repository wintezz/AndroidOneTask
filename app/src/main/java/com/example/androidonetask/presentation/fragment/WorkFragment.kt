package com.example.androidonetask.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.mvp.Contract
import com.example.androidonetask.mvp.Presenter
import com.example.androidonetask.presentation.adapter.MusicAdapter

class WorkFragment : Fragment(), Contract.View {

    private var presenter: Presenter? = null
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView,
        listenerPosition = {}
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

        presenter = Presenter(this)

        presenter?.handleApi()
        showLoading()
        initRecyclerView()
        clickViewError()
    }

    override fun onDestroyView() {
        _binding = null
        presenter?.onDestroyView()
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun clickViewError() {
        binding.imageRepeatRequest.setOnClickListener {
            presenter?.handleApi()
            showLoading()
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    override fun showContent(data: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(data)
            progressBar.isGone = true
        }
    }

    override fun showLoading() {
        with(binding) {
            textViewError.isGone = true
            imageRepeatRequest.isGone = true
            progressBar.isVisible = true
        }
    }

    override fun showError() {
        with(binding) {
            progressBar.isGone = true
            recView.isGone = true
            textViewError.isVisible = true
            textViewError.isClickable = true
            imageRepeatRequest.isVisible = true
        }
    }
}

