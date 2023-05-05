package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidonetask.R
import com.example.androidonetask.databinding.CardElementPagerBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.load

class CardDelegate :
    BaseDelegate<CardDelegate.CardViewHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.card_element_pager

    override fun equals(other: Any?) =
        (other is CardDelegate)
            && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return CardViewHolder(
            CardElementPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: CardViewHolder, items: Item
    ) {
        (items as? Item.AlbumUiModel)?.let(holder::bind)
    }

    override fun isForViewType(items: Item): Boolean = items is Item.AlbumUiModel

    inner class CardViewHolder(private val binding: CardElementPagerBinding) :
        BaseViewHolder(binding.root) {

        fun bind(item: Item.AlbumUiModel) {
            binding.albumName.text = item.name
            binding.albumName2.text = item.name
            binding.albumName3.text = item.name
            binding.artistName.text = item.artistName
            binding.artistName2.text = item.artistName
            binding.artistName3.text = item.artistName
            item.image?.let { binding.album.load(it) }
            item.image?.let { binding.album2.load(it) }
            item.image?.let { binding.album3.load(it) }
        }
    }
}