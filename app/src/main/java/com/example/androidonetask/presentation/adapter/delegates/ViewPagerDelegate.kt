package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ViewPagerBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.model.Item

class ViewPagerDelegate :
    BaseDelegate<ViewPagerDelegate.ViewPagerHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.view_pager

    override fun equals(other: Any?) =
        (other is ViewPagerDelegate)
                && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return ViewPagerHolder(
            ViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewPagerHolder, items: Item
    ) {
        (items as? Item.PagerUiModel)?.let(holder::bind)
    }

    override fun isForViewType(items: Item): Boolean = items is Item.PagerUiModel

    inner class ViewPagerHolder(private val binding: ViewPagerBinding) :
        BaseViewHolder(binding.root) {

        private var adapter = DelegateAdapter(
            delegates = listOf(CardDelegate())
        )

        fun bind(item: Item.PagerUiModel) {
            binding.viewPager.adapter = adapter
            adapter.updateItem(item.results)
        }
    }
}