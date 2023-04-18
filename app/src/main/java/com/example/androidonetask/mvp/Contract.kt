package com.example.androidonetask.mvp

interface Contract {

    interface View {
        fun showContent()
        fun showLoading()
        fun showError()
    }

    interface Model {
    }

    interface Presenter {
        fun onDestroyView()
    }
}