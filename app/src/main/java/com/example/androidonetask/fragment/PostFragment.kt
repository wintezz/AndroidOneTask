package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.textRankPost.text = arguments?.getInt(POSITION_KEY).toString()

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

         const val POSITION_KEY = "Int"

        /*fun newInstance(id: Int) = PostFragment().apply {
            arguments = bundleOf(KEY_FOR_ID to id)
        }
    }*/}
}