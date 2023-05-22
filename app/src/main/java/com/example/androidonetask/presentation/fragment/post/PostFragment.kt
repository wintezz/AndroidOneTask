package com.example.androidonetask.presentation.fragment.post

import android.os.Bundle
import android.view.View
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentPostBinding
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.viewmodel.post.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment :
    BaseFragment<PostViewModel, FragmentPostBinding>(FragmentPostBinding::inflate) {

    override fun getFragmentView() = R.layout.fragment_post

    override fun getViewModelClass() = PostViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        binding.textRankPost.text = arguments?.getInt(ADAPTER_POSITION).toString()
    }

    companion object {
        const val ADAPTER_POSITION = "adapterPosition"
    }
}