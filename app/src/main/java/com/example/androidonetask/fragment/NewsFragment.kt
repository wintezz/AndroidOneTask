package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.FragmentAdapter
import com.example.androidonetask.databinding.FragmentNewsBinding
import com.example.androidonetask.utils.RankElement

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding
            .inflate(
                inflater, container,
                false
            )

        activity?.title = "NewsFragment"

        binding.recViewNews.layoutManager = LinearLayoutManager(activity)
        binding.recViewNews.adapter = FragmentAdapter(RankElement.fillList())

        return binding.root
    }
}