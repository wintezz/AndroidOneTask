package com.example.androidonetask.fragment

import android.os.Bundle
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
import com.example.androidonetask.utils.RankElement

class WorksFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = WorkAdapter { onClickView() }
    private lateinit var apiService: ApiService
    private var handler = HandlerThread(String())

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
        adapter.updateList(RankElement.fillList())
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initHandlerThread() {
        handler.start()
        Thread(Runnable {
            getData()
        }).start()
    }

    private fun getData() {
        apiService.getTrackList().execute()
    }
}

