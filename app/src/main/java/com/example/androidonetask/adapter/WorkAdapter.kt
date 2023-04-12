package com.example.androidonetask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.data.Track
import com.example.androidonetask.databinding.TrackElementListBinding
import com.squareup.picasso.Picasso

class WorkAdapter(
    private val listenerImage: (View) -> Unit
) : RecyclerView.Adapter<WorkAdapter.ArtistViewHolder>() {

    private var tracks: List<Track> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.track_element_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.onBind(tracks[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newTracks: List<Track>) {
        this.tracks = newTracks
        notifyDataSetChanged()
    }

    private fun ImageView.load(url: String) {
        Picasso.get()
            .load(url)
            .into(this)
    }

    override fun getItemCount(): Int = tracks.size

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TrackElementListBinding.bind(view)

        fun onBind(data: Track) {

            binding.name.text = data.name
            binding.artistName.text = data.artistName
            binding.duration.progress = data.duration!!.toInt()
            data.albumImage?.let { binding.albumImage.load(it) }

            binding.albumImage.setOnClickListener {
                listenerImage.invoke(binding.albumImage)
            }
        }
    }
}
