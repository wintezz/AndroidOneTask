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
import com.example.androidonetask.adapter.WorkAdapter
import com.example.androidonetask.data.ApiService
import com.example.androidonetask.data.Repository
import com.example.androidonetask.databinding.FragmentArtistBinding

class WorksFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = WorkAdapter { onClickView() }
    private lateinit var apiService: ApiService

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

        apiService = Repository.retrofitService

        initRecyclerView()
        initHandlerThread()
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initHandlerThread() {
        val handlerThread = HandlerThread(HANDLER_NAME)
        handlerThread.start()
        val looper = handlerThread.looper
        val handler = Handler(looper)
        handler.post(Runnable {
            getData()
        })
    }

    private fun getData() {
        val list = apiService.getTrackList().execute().body()?.results
        if (list != null) {
            requireActivity().runOnUiThread(
                Runnable {
                    adapter.updateList(list)
                }
            )
        }
    }

    companion object {
        private const val HANDLER_NAME = "WorkHandler"
    }
}

