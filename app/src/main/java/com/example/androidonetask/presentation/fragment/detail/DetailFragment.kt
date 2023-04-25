package com.example.androidonetask.presentation.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(
            this,
            DetailViewModelFactory(repository = RepositoryImpl())
        )[DetailViewModel::class.java]
    }
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding
            .inflate(
                inflater, container,
                false
            )
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
}
