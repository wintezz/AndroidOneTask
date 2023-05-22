package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidonetask.R
import com.example.androidonetask.databinding.LoaderElementListBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.model.Item

class LoaderDelegate :
    BaseDelegate<LoaderDelegate.LoaderViewHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.loader_element_list

    override fun equals(other: Any?) =
        (other is LoaderDelegate)
                && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return LoaderViewHolder(
            LoaderElementListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, items: Item) {
    }

    override fun isForViewType(items: Item): Boolean = items is Item.LoaderUiModel

    inner class LoaderViewHolder(binding: LoaderElementListBinding) :
        BaseViewHolder(binding.root)
}