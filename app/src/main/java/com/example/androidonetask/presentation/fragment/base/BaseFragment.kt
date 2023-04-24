package com.example.androidonetask.presentation.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.androidonetask.databinding.FragmentBaseBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _rootBinding: FragmentBaseBinding? = null
    private val rootBinding get() = _rootBinding!!
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    lateinit var model: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _rootBinding = FragmentBaseBinding.inflate(inflater, null, false)
        _binding = bindingInflater.invoke(inflater)
        rootBinding.frameLayout.addView(
            binding.root
        )

        return rootBinding.root
    }

    abstract fun getFragmentView(): Int

    abstract fun getViewModel(): Class<VM>

    open fun showError() {
        with(rootBinding) {
            progressBar.isVisible = false
            recView.isVisible = false
            textViewError.isVisible = true
            imageRepeatRequest.isVisible = true
        }
    }

    open fun showLoading() {
        model.progress.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading)
                with(rootBinding) {
                    textViewError.isVisible = false
                    imageRepeatRequest.isVisible = false
                    progressBar.isVisible = true
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}



