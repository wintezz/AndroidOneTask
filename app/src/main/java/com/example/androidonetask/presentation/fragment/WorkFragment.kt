package com.example.androidonetask.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.data.Repository
import com.example.androidonetask.data.model.TrackMapper
import com.example.androidonetask.databinding.FragmentArtistBinding

class WorkFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var handlerThread = HandlerThread(HANDLER_NAME)
    private var adapter = MusicAdapter(
        listenerAlbumImage = ::onClickView,
        listenerArtistName = {},
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

        binding.progressBar.visibility = View.VISIBLE

        initRecyclerView()
        initHandlerThread()
        onClickRequest()
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun initHandlerThread() {
        handlerThread.start()
        val looper = handlerThread.looper
        val handler = Handler(looper)
        handler.post {
            try {
                val list = Repository.getTracks().map { TrackMapper.buildFrom(it) }
                requireActivity().runOnUiThread {
                    adapter.updateList(list)
                    binding.progressBar.visibility = View.GONE
                }
            } catch (e: Throwable) {
                requireActivity().runOnUiThread {
                    binding.textViewError.visibility = TextureView.VISIBLE
                    binding.imageRepeatRequest.visibility = ImageView.VISIBLE
                }
            } finally {
                requireActivity().runOnUiThread {
                    binding.textViewError.visibility = TextureView.GONE
                    binding.imageRepeatRequest.visibility = ImageView.GONE
                }
            }
        }
    }

    private fun onClickRequest() {
        binding.imageRepeatRequest.setOnClickListener {
            initHandlerThread()
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    override fun onDestroyView() {
        _binding = null
        handlerThread.quit()
        super.onDestroyView()
    }

    companion object {
        private const val HANDLER_NAME = "WorkHandler"
    }
}

