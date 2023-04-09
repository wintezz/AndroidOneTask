package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ListElementBinding
import com.example.androidonetask.utils.ClickListener

class NewsAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var elements: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(elements[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newElements: List<String>) {
        this.elements = newElements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = elements.size

    inner class NewsViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        val binding = ListElementBinding.bind(view)

        fun onBind(elem: String) {
            binding.textRank.text = elem

            binding.textRank.setOnClickListener {
                listener.onClickItem(adapterPosition)
            }

            binding.artView.setOnClickListener {
                listener.onClickView(binding.artView)
            }
        }
    }
}
