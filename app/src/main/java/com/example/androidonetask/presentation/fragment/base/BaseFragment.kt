package com.example.androidonetask.presentation.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidonetask.databinding.BaseFragmentBinding

open class BaseFragment : Fragment() {

    private var _binding: BaseFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BaseFragmentBinding
            .inflate(
                inflater, container,
                false
            )
        return binding.root
    }

/*    open fun showLoading() {
        with(binding) {
            textViewError.isVisible = false
            imageRepeatRequest.isVisible = false
            progressBar.isVisible = true
        }
    }

    open fun showError() {
        with(binding) {
            progressBar.isVisible = false
            recView.isVisible = false
            textViewError.isVisible = true
            imageRepeatRequest.isVisible = true
        }
    }*/

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}