package com.example.androidonetask.presentation.adapter.delegates

import android.view.ViewGroup
import com.example.androidonetask.presentation.adapter.BaseViewHolder

interface BaseDelegate<out VH : BaseViewHolder, T> {

    val viewTypeViewBinding: Int

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun onBindViewHolder(holder: @UnsafeVariance VH, items: T)

    fun isForViewType(items: T): Boolean
}