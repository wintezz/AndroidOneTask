package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.FragmentAdapter
import com.example.androidonetask.databinding.FragmentWorksBinding
import com.example.androidonetask.utils.RankElement


class WorksFragment : Fragment() {

    private lateinit var binding: FragmentWorksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorksBinding
            .inflate(
                inflater, container,
                false
            )

        activity?.title = "WorksFragment"

        binding.recViewWorks.layoutManager = LinearLayoutManager(activity)
        binding.recViewWorks.adapter = FragmentAdapter(RankElement.fillList())

        return binding.root
    }
}