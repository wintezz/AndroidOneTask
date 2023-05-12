package com.example.androidonetask.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.presentation.adapter.delegates.BaseDelegate
import com.example.androidonetask.presentation.model.Item

class DelegateAdapter(
    private val delegates: List<BaseDelegate<BaseViewHolder, Item>>
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    init {
        checkDuplicates()
    }

    private var tracks: List<Item> = emptyList()
    private val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegates.firstOrNull { it.viewTypeViewBinding == viewType }
            ?.onCreateViewHolder(parent)
            ?: throw IllegalArgumentException("Invalid ViewType Provided: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegates.firstOrNull { it.viewTypeViewBinding == getItemViewType(position) }
            ?.onBindViewHolder(holder, tracks[position])
    }

    fun updateItem(newTracks: List<Item>) {
        this.tracks = newTracks
        differ.submitList(newTracks)
    }

    private fun <T> hasDuplicates(arr: List<T>): Boolean {
        return arr.size != arr.distinct().count()
    }

    private fun checkDuplicates() {
        val viewTypes = delegates.map { it.viewTypeViewBinding }.toList()
        if (hasDuplicates(viewTypes)) {
            throw Exception("We have duplicate: $viewTypes")
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return delegates.firstOrNull { it.isForViewType(tracks[position]) }?.viewTypeViewBinding
            ?: throw Exception("Can not get viewType for position: $position")
    }

    companion object {

        val diffCallBack = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}