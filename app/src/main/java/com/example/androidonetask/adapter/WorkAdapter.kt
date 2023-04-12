package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.data.TrackList
import com.example.androidonetask.databinding.TrackElementListBinding
import com.squareup.picasso.Picasso

class WorkAdapter(
    private val listenerImage: (View) -> Unit
) : RecyclerView.Adapter<WorkAdapter.ArtistViewHolder>() {

    private var trackLists: List<TrackList> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.track_element_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.onBind(trackLists[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newTrackLists: List<TrackList>) {
        this.trackLists = newTrackLists
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = trackLists.size

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TrackElementListBinding.bind(view)

        fun onBind(data: TrackList) {

            binding.name.text = data.name
            binding.artistName.text = data.artist_name
            binding.duration.progress = data.duration.toInt()

            binding.albumImage.setOnClickListener {
                listenerImage.invoke(binding.albumImage)
            }

            Picasso.get()
                .load(data.album_image)
                .into(binding.albumImage)
        }
    }
}
