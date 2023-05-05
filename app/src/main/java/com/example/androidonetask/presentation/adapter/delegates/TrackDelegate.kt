package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidonetask.R
import com.example.androidonetask.databinding.TrackElementListBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.load

class TrackDelegate(
    private var onItemClickViewHolder: () -> Unit = {},
    private var onItemClickNameHolder: () -> Unit = {},
    private var onItemClickPositionHolder: (Int) -> Unit = {}
) :
    BaseDelegate<TrackDelegate.TrackViewHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.track_element_list

    override fun equals(other: Any?) =
        (other is TrackDelegate)
                && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return TrackViewHolder(
            TrackElementListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, items: Item) {
        (items as? Item.TrackUiModel)?.let(holder::bind)
    }

    override fun isForViewType(items: Item): Boolean = items is Item.TrackUiModel

    inner class TrackViewHolder(private val binding: TrackElementListBinding) :
        BaseViewHolder(binding.root) {

        fun bind(item: Item.TrackUiModel) {
            binding.name.text = item.name
            binding.artistName.text = item.artistName
            item.duration?.let { binding.duration.progress = it.toInt() }
            item.albumImage?.let { binding.albumImage.load(it) }

            binding.albumImage.setOnClickListener {
                onItemClickViewHolder.invoke()
            }

            binding.maskGroup.setOnClickListener {
                onItemClickPositionHolder.invoke(adapterPosition)

            }

            binding.artistName.setOnClickListener {
                onItemClickNameHolder.invoke()
            }
        }
    }
}