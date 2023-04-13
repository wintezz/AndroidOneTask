package com.example.androidonetask.fragment

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.adapter.MusicAdapter
import com.example.androidonetask.data.Repository
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

        initRecyclerView()
        initHandlerThread()
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
            val list = Repository.getTracks()
            requireActivity().runOnUiThread {
                adapter.updateList(list)
            }
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

