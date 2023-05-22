package com.example.androidonetask.presentation.fragment.detail

import android.os.Bundle
import android.view.View
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentDetailBinding
import com.example.androidonetask.di.ApplicationComponent
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.viewmodel.detail.DetailViewModel
import com.example.androidonetask.presentation.viewmodel.detail.DetailViewModelFactory
import javax.inject.Inject

class DetailFragment :
    BaseFragment<DetailViewModel, FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    override fun getFragmentView() = R.layout.fragment_detail

    override fun inject(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun getViewModelClass() = DetailViewModel::class.java

    @Inject
    override lateinit var getViewModelFactory: DetailViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }
}