package com.example.androidonetask.mvp

import com.example.androidonetask.data.model.TrackUiModel

interface Contract {

    interface View {
        fun showContent(data: List<TrackUiModel>)
        fun showLoading()
        fun showError()
    }

    interface Model {
    }

    interface Presenter {
        fun handleApi()
        fun onDestroyView()
    }
}