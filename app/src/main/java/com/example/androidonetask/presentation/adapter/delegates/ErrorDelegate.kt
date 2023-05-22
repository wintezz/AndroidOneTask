package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ErrorElementListBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.model.Item

class ErrorDelegate(
    private var onItemClickViewHolder: () -> Unit = {}
) :
    BaseDelegate<ErrorDelegate.ErrorViewHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.error_element_list

    override fun equals(other: Any?) =
        (other is ErrorDelegate)
                && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return ErrorViewHolder(
            ErrorElementListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ErrorViewHolder, items: Item) {
        (items as? Item.ErrorUiModel)?.let(holder::bind)
    }

    override fun isForViewType(items: Item): Boolean = items is Item.ErrorUiModel

    inner class ErrorViewHolder(private val binding: ErrorElementListBinding) :
        BaseViewHolder(binding.root) {

        fun bind(item: Item.ErrorUiModel) {
            binding.textViewError.text = item.title

            binding.imageRepeatRequest.setOnClickListener {
                onItemClickViewHolder.invoke()
            }
        }
    }
}