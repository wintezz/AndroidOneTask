package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.databinding.FragmentExpositionsBinding


class ExpositionsFragment : Fragment() {

    private lateinit var binding: FragmentExpositionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpositionsBinding
            .inflate( inflater,container, false)

        return binding.root
    }
}