package com.example.androidonetask.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.AppState
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.presentation.utils.TrackMapper

class WorkFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
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

        showLoading()
        initRecyclerView()
        initHandlerThread()
        handleApi()
        clickViewError()
    }

    override fun onDestroyView() {
        _binding = null
        handlerThread.quit()
        super.onDestroyView()
    }

    private fun initHandlerThread() {
        handlerThread = HandlerThread(HANDLER_NAME)
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun clickViewError() {
        binding.imageRepeatRequest.setOnClickListener {
            handleApi()
            showLoading()
        }
    }

    private fun handleApi() {
        handler.post {
            when (val response = Repository.getTracks()) {
                is AppState.Success -> {
                    val data = response.data
                    val list = TrackMapper.buildFrom(data)
                    binding.root.post {
                        showContent(list)
                    }
                }
                else -> {
                    binding.root.post {
                        showError()
                    }
                }
            }
        }
    }

    private fun showContent(tracks: List<TrackUiModel>) {
        with(binding) {
            adapter.updateList(tracks)
            progressBar.isGone = true
        }
    }

    private fun showLoading() {
        with(binding) {
            textViewError.isGone = true
            imageRepeatRequest.isGone = true
            progressBar.isVisible = true
        }
    }

    private fun showError() {
        with(binding) {
            progressBar.isGone = true
            recView.isGone = true
            textViewError.isVisible = true
            textViewError.isClickable = true
            imageRepeatRequest.isVisible = true
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    companion object {
        private const val HANDLER_NAME = "WorkHandler"
    }
}

