package com.example.androidonetask.mvp.work

import com.example.androidonetask.data.model.TrackUiModel

interface WorkContract {

    interface View {
        fun showContent(data: List<TrackUiModel>)
        fun showLoading()
        fun showError()
    }

    interface Presenter {
        fun handleApi()
        fun onDestroyView()
    }
}