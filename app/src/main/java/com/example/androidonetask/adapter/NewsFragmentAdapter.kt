package com.example.androidonetask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ListElementBinding
import com.example.androidonetask.utils.ClickListener

class NewsFragmentAdapter(
    private val elements: List<String>,
    private val listener: ClickListener
) : RecyclerView.Adapter<NewsFragmentAdapter.MyViewHolder>() {

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
        holder.binding.imageView

        holder.binding.textRank.setOnClickListener {
            listener.onClickItem(position)
        }

        holder.binding.imageView.setOnClickListener {
            listener.onClickView(holder.binding.imageView)
        }
    }

    override fun getItemCount(): Int = elements.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListElementBinding.bind(itemView)

    }
}
