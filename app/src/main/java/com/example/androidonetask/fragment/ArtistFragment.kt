package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.FragmentAdapter
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.utils.RankElement

class ArtistFragment : Fragment() {

    private lateinit var binding: FragmentArtistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistBinding
            .inflate(
                inflater, container,
                false
            )

        activity?.title = "ArtistFragment"

        binding.recViewArtist.layoutManager = LinearLayoutManager(activity)
        binding.recViewArtist.adapter = FragmentAdapter(RankElement.fillList())

        return binding.root
    }
}