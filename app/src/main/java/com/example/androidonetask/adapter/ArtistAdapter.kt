package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ListElementBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    private lateinit var elements: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.onBind(elements[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newElements: List<String>) {
        this.elements = newElements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = elements.size

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListElementBinding.bind(view)

        fun onBind(elem: String) {
            binding.textRank.text = elem
        }
    }
}