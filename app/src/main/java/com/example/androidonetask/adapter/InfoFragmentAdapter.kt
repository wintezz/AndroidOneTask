package com.example.androidonetask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ListElementBinding
import com.example.androidonetask.utils.ClickListenerDetail

class InfoFragmentAdapter(
    private val elements: List<String>,
    private val listener: ClickListenerDetail
) : RecyclerView.Adapter<InfoFragmentAdapter.InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.onBind(elements[position])
    }

    override fun getItemCount(): Int = elements.size

    inner class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListElementBinding.bind(itemView)

        fun onBind(elem: String) {
            binding.textRank.text = elements[position]

            binding.detailView.setOnClickListener {
                listener.onClickView(binding.detailView)
            }
        }
    }
}