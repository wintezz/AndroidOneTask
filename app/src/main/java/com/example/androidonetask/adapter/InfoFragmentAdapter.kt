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
) : RecyclerView.Adapter<InfoFragmentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.textRank.text = elements[position]
        holder.binding.topText.text
        holder.binding.botText.text
        holder.binding.detailView

        holder.binding.detailView.setOnClickListener {
            listener.onClickView(holder.binding.detailView)
        }
    }

    override fun getItemCount(): Int = elements.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListElementBinding.bind(itemView)

    }
}