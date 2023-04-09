package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.ArtistAdapter
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.utils.RankElement

class ArtistFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = ArtistAdapter(RankElement.fillList())

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

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        adapter.updateList(RankElement.fillList())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}