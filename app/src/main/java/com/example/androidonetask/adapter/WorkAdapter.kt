package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.data.Track
import com.example.androidonetask.databinding.ListElementBinding
import com.squareup.picasso.Picasso

class WorkAdapter(
    private val listenerImage: (View) -> Unit
) : RecyclerView.Adapter<WorkAdapter.ArtistViewHolder>() {

    private lateinit var tracks: MutableList<Track>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.onBind(tracks[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newTracks: MutableList<Track>) {
        this.tracks = newTracks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tracks.size

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListElementBinding.bind(view)

        fun onBind(data: Track) {
            binding.textRank.text = data.toString()

            binding.artView.setOnClickListener {
                listenerImage.invoke(binding.artView)
            }

            Picasso.get()
                .load(data.albumImage)
        }
    }
}
