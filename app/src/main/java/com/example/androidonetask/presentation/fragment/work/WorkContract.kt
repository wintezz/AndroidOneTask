package com.example.androidonetask.presentation.fragment.work

import com.example.androidonetask.data.model.TrackUiModel

interface WorkContract {

    interface View {
        fun showContent(data: List<TrackUiModel>)
        fun showError()
    }

    interface Presenter {
        fun loadTracks()
        fun onDestroyView()
    }
}