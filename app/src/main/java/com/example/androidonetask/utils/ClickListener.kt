package com.example.androidonetask.utils

import android.view.View

interface ClickListener {
    fun onClickItem(position: Int)

    fun onClickView(view: View)
}
