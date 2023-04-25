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

abstract class BaseFragment<ViewModel : BaseViewModel, VBinding : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VBinding
) : Fragment() {

    private var _rootBinding: FragmentBaseBinding? = null
    private val rootBinding get() = _rootBinding!!
    private var _binding: VBinding? = null
    val binding: VBinding get() = _binding!!

    protected abstract fun getFragmentView(): Int

    protected lateinit var viewModel: ViewModel
    protected abstract fun getViewModel(): Class<ViewModel>
    protected abstract fun getViewModelFactory(): ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, getViewModelFactory())[getViewModel()]
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerLoading()
        clickViewError()
    }

    open fun showError() {
        with(rootBinding) {
            progressBar.isVisible = false
            textViewError.isVisible = true
            imageRepeatRequest.isVisible = true
        }
    }

    open fun observerLoading() {
        viewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading == true)
                with(rootBinding) {
                    progressBar.isVisible = true
                    imageRepeatRequest.isVisible = false
                    textViewError.isVisible = false
                } else {
                (isLoading == false)
                with(rootBinding) {
                    progressBar.isVisible = false
                }
            }
        }
    }

    private fun clickViewError() {
        rootBinding.imageRepeatRequest.setOnClickListener {
            viewModel.reloadRequest()
        }
    }

    override fun onDestroyView() {
        _rootBinding = null
        _binding = null
        super.onDestroyView()
    }
}




