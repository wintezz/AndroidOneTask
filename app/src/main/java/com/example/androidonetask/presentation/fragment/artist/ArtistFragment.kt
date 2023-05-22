package com.example.androidonetask.presentation.fragment.artist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.TrackDelegate
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.utils.fillList
import com.example.androidonetask.presentation.viewmodel.artist.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment :
    BaseFragment<ArtistViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private var adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickViewHolder = ::onClickView
            )
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist

    override fun getViewModelClass() = ArtistViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        showContent()
        initRecyclerView()
    }

    private fun showContent() {
        adapter.updateItem(fillList())
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_artistFragment_to_imageViewActivity)
    }
}