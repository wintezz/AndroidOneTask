package com.example.androidonetask.presentation.fragment.detail

import android.os.Bundle
import android.view.View
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentDetailBinding
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.viewmodel.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<DetailViewModel, FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    override fun getFragmentView() = R.layout.fragment_detail

    override fun getViewModelClass() = DetailViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }
}