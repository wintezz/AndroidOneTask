package com.example.androidonetask.presentation.fragment.news

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.TrackDelegate
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.fragment.post.PostFragment.Companion.ADAPTER_POSITION
import com.example.androidonetask.presentation.utils.fillList
import com.example.androidonetask.presentation.viewmodel.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment :
    BaseFragment<NewsViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private var adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickPositionHolder = ::onClickItemPosition,
                onItemClickNameHolder = ::onClickImageView,
                onItemClickViewHolder = ::onClickArtist
            )
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist

    override fun getViewModelClass() = NewsViewModel::class.java

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

    private fun onClickItemPosition(position: Int) {
        findNavController().navigate(
            R.id.action_newsFragment_to_postFragment,
            bundleOf(ADAPTER_POSITION to position)
        )
    }

    private fun onClickImageView() {
        findNavController().navigate(R.id.action_newsFragment_to_imageViewActivity)
    }

    private fun onClickArtist() {
        findNavController().navigate(R.id.action_newsFragment_to_artistFragment)
    }
}