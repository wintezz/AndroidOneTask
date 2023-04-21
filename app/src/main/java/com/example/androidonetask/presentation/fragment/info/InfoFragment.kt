package com.example.androidonetask.presentation.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.MusicAdapter
import com.example.androidonetask.presentation.utils.fillList

class InfoFragment : Fragment() {

    private val viewModel: InfoViewModel by lazy {
        ViewModelProvider(
            this,
            InfoViewModelFactory(repository = RepositoryImpl())
        )[InfoViewModel::class.java]
    }
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter =
        MusicAdapter(
            listenerAlbumImage = ::onClickView
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding
            .inflate(
                inflater, container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        showContent()
        initRecyclerView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showContent() {
        with(binding) {
            adapter.updateList(fillList())
            progressBar.isVisible = false
        }
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_infoFragment_to_detailFragment)
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }
}