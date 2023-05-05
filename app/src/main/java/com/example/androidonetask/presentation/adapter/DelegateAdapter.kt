package com.example.androidonetask.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.presentation.adapter.delegates.BaseDelegate
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.DiffUtilsCallBack

class DelegateAdapter(
    private val delegates: List<BaseDelegate<BaseViewHolder, Item>>
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    init {
        main()
    }

    private var tracks: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegates.firstOrNull { it.viewTypeViewBinding == viewType }
            ?.onCreateViewHolder(parent)
            ?: throw IllegalArgumentException("Invalid ViewType Provided")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegates.firstOrNull { it.viewTypeViewBinding == getItemViewType(position) }
            ?.onBindViewHolder(holder, tracks[position])
    }

    fun updateItem(newTracks: List<Item>) {
        val diffUtilsCallBack = DiffUtilsCallBack(tracks, newTracks)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallBack)
        this.tracks = newTracks
        diffResult.dispatchUpdatesTo(this)
    }

    fun main() {
        delegates.toSet()
    }

    override fun getItemCount(): Int = tracks.size

    override fun getItemViewType(position: Int): Int {
        return delegates.firstOrNull { it.isForViewType(tracks[position]) }?.viewTypeViewBinding
            ?: throw Exception("Can not get viewType for position $position")
    }
}