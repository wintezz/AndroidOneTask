package com.example.androidonetask.presentation.fragment.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentPostBinding
import com.example.androidonetask.presentation.viewmodel.post.PostViewModel
import com.example.androidonetask.presentation.viewmodel.post.PostViewModelFactory

class PostFragment : Fragment() {

    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(
            this,
            PostViewModelFactory(repository = RepositoryImpl())
        )[PostViewModel::class.java]
    }
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        binding.textRankPost.text = arguments?.getInt(ADAPTER_POSITION).toString()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val ADAPTER_POSITION = "adapterPosition"
    }
}