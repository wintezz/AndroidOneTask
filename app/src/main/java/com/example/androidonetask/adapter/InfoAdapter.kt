package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.data.Track
import com.example.androidonetask.databinding.ListElementBinding

class InfoAdapter(
    private val listener: (View) -> Unit
) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    private var elements: List<Track> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.onBind(elements[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newElements: List<Track>) {
        this.elements = newElements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = elements.size

    inner class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListElementBinding.bind(itemView)

        fun onBind(elem: Track) {
            binding.textRank.text = elem.id

            binding.detailView.setOnClickListener {
                listener.invoke(binding.detailView)
            }
        }
    }
}