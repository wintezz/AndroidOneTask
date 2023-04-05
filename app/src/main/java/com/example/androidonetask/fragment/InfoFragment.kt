package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.InfoFragmentAdapter
import com.example.androidonetask.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding
            .inflate( inflater,container,
                false)

        activity?.title = "InfoFragment"

        binding.recViewInfo.layoutManager = LinearLayoutManager(activity)
        binding.recViewInfo.adapter = InfoFragmentAdapter(fillList())

        return binding.root
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..1000).forEach { i -> data.add("$i") }
        return data
    }
}