package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.data.Track
import com.example.androidonetask.databinding.ListElementBinding

class NewsAdapter(
    private val listener: (Int) -> Unit,
    private val listenerArtView: (View) -> Unit,
    private val listenerArtist: (TextView) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var elements: List<Track> = emptyList()

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
    fun updateList(newElements: List<Track>) {
        this.elements = newElements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = elements.size

    inner class NewsViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        val binding = ListElementBinding.bind(view)

        fun onBind(elem: Track) {

            binding.textRank.text = elem.id

            binding.textRank.setOnClickListener {
                listener.invoke(adapterPosition)
            }

            binding.imageView.setOnClickListener {
                listenerArtView.invoke(binding.imageView)
            }

            binding.botText.setOnClickListener {
                listenerArtist.invoke(binding.botText)
            }
        }
    }
}
