package com.example.androidonetask.presentation.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.androidonetask.databinding.FragmentBaseBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> FragmentBaseBinding
) : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    val binding: FragmentBaseBinding
        get() = _binding as FragmentBaseBinding

    private val viewModel: VM by lazy {
        ViewModelProvider(this)[getViewModel()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
    }

    abstract fun getFragmentView(): Int

    abstract fun getViewModel(): Class<VM>

    open fun showError() {
        with(binding) {
            progressBar.isVisible = false
            recView.isVisible = false
            textViewError.isVisible = true
            imageRepeatRequest.isVisible = true
        }
    }

    open fun showLoading() {
        viewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading != false)
                with(binding) {
                    textViewError.isVisible = false
                    imageRepeatRequest.isVisible = false
                    progressBar.isVisible = true
                } else {
                isLoading != true
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}



