package com.example.androidonetask.presentation.fragment.expositions

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.di.ApplicationComponent
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.TrackDelegate
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.utils.fillList
import com.example.androidonetask.presentation.viewmodel.expositions.ExpositionsViewModel
import com.example.androidonetask.presentation.viewmodel.expositions.ExpositionsViewModelFactory
import javax.inject.Inject

class ExpositionsFragment :
    BaseFragment<ExpositionsViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate) {

    private var adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickViewHolder = ::onClickItem
            )
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist

    override fun inject(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun getViewModelClass() = ExpositionsViewModel::class.java

    @Inject
    override lateinit var getViewModelFactory: ExpositionsViewModelFactory

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

    private fun onClickItem() {
        findNavController().navigate(R.id.action_expositionsFragment_to_artActivity)
    }
}