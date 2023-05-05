package com.example.androidonetask.presentation.fragment.expositions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.data.repository.RepositoryImpl
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.TrackDelegate
import com.example.androidonetask.presentation.utils.fillList
import com.example.androidonetask.presentation.viewmodel.expositions.ExpositionsViewModel
import com.example.androidonetask.presentation.viewmodel.expositions.ExpositionsViewModelFactory

class ExpositionsFragment : Fragment() {

    private val viewModel: ExpositionsViewModel by lazy {
        ViewModelProvider(
            this,
            ExpositionsViewModelFactory(repository = RepositoryImpl())
        )[ExpositionsViewModel::class.java]
    }
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickViewHolder = ::onClickItem
            )
        )
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
        adapter.updateItem(fillList())
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun onClickItem() {
        findNavController().navigate(R.id.action_expositionsFragment_to_artActivity)
    }
}