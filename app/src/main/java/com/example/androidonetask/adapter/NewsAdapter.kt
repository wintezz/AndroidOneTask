package com.example.androidonetask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ListElementBinding
import com.example.androidonetask.utils.ClickListener

class NewsAdapter(
    private var elements: List<String>,
    private val listener: ClickListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    /*private val elements = emptyList<String>()*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(elements[position])
    }

    override fun getItemCount(): Int = elements.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListElementBinding.bind(itemView)

        fun onBind(elem: String) {
            binding.textRank.text = elements[adapterPosition]

            binding.textRank.setOnClickListener {
                listener.onClickItem(adapterPosition)
            }

            binding.artView.setOnClickListener {
                listener.onClickView(binding.artView)
            }
        }
    }
}
