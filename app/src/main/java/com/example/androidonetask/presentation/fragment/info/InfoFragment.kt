package com.example.androidonetask.presentation.fragment.info

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
import com.example.androidonetask.presentation.viewmodel.info.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment :
    BaseFragment<InfoViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private var adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickViewHolder = ::onClickView
            )
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist

    override fun getViewModelClass() = InfoViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        showContent()
        initRecyclerView()
    }

    private fun showContent() {
        adapter.updateItem(fillList())
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_infoFragment_to_detailFragment)
    }

    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
    }
}