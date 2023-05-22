package com.example.androidonetask.presentation.fragment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.androidonetask.MusicApplication
import com.example.androidonetask.databinding.FragmentBaseBinding
import com.example.androidonetask.di.ApplicationComponent
import com.example.androidonetask.presentation.viewmodel.base.BaseViewModel

abstract class BaseFragment<ViewModel : BaseViewModel, VBinding : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VBinding
) : Fragment() {

    private var _rootBinding: FragmentBaseBinding? = null
    private val rootBinding get() = _rootBinding!!
    private var _binding: VBinding? = null
    val binding: VBinding get() = _binding!!

    protected abstract fun getFragmentView(): Int

    protected lateinit var viewModel: ViewModel

    protected abstract fun inject(applicationComponent: ApplicationComponent)

    protected abstract fun getViewModelClass(): Class<ViewModel>

    protected abstract val getViewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as? MusicApplication)?.applicationComponent?.let(::inject)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, getViewModelFactory)[getViewModelClass()]
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
        onButtonRetryClick()
        observerError()
    }

    open fun observerLoading() {
        viewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            with(rootBinding) {
                progressBar.isVisible = isLoading
                imageRepeatRequest.isVisible = false
                textViewError.isVisible = false
            }
        }
    }

    open fun onButtonRetryClick() {
        rootBinding.imageRepeatRequest.setOnClickListener {
            viewModel.onButtonRetryClick()
        }
    }

    open fun observerError() {
        viewModel.error.observe(viewLifecycleOwner) { isError ->
            rootBinding.imageRepeatRequest.isVisible = isError
            rootBinding.textViewError.isVisible = isError
        }
    }

    override fun onDestroyView() {
        _rootBinding = null
        _binding = null
        super.onDestroyView()
    }
}