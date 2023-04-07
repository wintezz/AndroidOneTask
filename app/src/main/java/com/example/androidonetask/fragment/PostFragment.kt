package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.androidonetask.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding
            .inflate(
                inflater, container,
                false
            )

        binding.textRankPost.text = id.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

       private const val KEY_FOR_ID = "KEY_FOR_ID"

        fun newInstance(id: String) = PostFragment().apply {
            arguments = bundleOf(KEY_FOR_ID to id)
        }
    }
}