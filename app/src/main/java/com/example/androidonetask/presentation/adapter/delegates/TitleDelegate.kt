package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidonetask.R
import com.example.androidonetask.databinding.TitleElementBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.model.Item

class TitleDelegate :
    BaseDelegate<TitleDelegate.TitleViewHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.title_element

    override fun equals(other: Any?) =
        (other is TitleDelegate)
                && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return TitleViewHolder(
            TitleElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TitleViewHolder, items: Item) {
        (items as? Item.TitleUiModel)?.let(holder::bind)
    }

    override fun isForViewType(items: Item): Boolean = items is Item.TitleUiModel

    inner class TitleViewHolder(private val binding: TitleElementBinding) :
        BaseViewHolder(binding.root) {

        fun bind(item: Item.TitleUiModel) {
            binding.title.text = item.title
        }
    }
}