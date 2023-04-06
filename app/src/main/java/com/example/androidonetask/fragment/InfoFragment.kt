package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.FragmentAdapter
import com.example.androidonetask.databinding.FragmentInfoBinding
import com.example.androidonetask.utils.RankElement


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding
            .inflate(
                inflater,container,
                false)

        activity?.title = "InfoFragment"

        binding.recViewInfo.layoutManager = LinearLayoutManager(activity)
        binding.recViewInfo.adapter = FragmentAdapter(RankElement.fillList())

        return binding.root
    }
}