package com.example.androidonetask.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.androidonetask.presentation.model.Item

internal class DiffUtilsCallBack(
    private val oldList: List<Item>,
    private val newList: List<Item>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == oldList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}