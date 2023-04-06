package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.ArtistFragmentAdapter
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

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }

    private fun initRecyclerView() {
        binding.recViewExpositions.layoutManager = LinearLayoutManager(context)
        binding.recViewExpositions.adapter = ArtistFragmentAdapter(RankElement.fillList())
    }
}