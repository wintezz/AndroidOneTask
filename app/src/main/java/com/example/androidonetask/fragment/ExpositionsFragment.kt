package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.ExpositionsFragmentAdapter
import com.example.androidonetask.databinding.FragmentExpositionsBinding
import com.example.androidonetask.utils.RankElement

class ExpositionsFragment : Fragment() {

    private lateinit var binding: FragmentExpositionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpositionsBinding
            .inflate(
                inflater, container,
                false
            )

        activity?.title = "ExpositionsFragment"

        binding.recViewExpositions.layoutManager = LinearLayoutManager(activity)
        binding.recViewExpositions.adapter = ExpositionsFragmentAdapter(RankElement.fillList())

        return binding.root
    }
}