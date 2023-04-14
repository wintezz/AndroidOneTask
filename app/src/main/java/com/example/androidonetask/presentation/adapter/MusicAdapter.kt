package com.example.androidonetask.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidonetask.R
import com.example.androidonetask.data.model.TrackUiModel
import com.example.androidonetask.databinding.TrackElementListBinding
import com.example.androidonetask.presentation.utils.load

class MusicAdapter(
    private val listenerPosition: (Int) -> Unit,
    private val listenerAlbumImage: () -> Unit = {},
    private val listenerArtistName: () -> Unit = {}
) : RecyclerView.Adapter<MusicAdapter.WorkViewHolder>() {

    private var tracks: List<TrackUiModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        return WorkViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.track_element_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        holder.onBind(tracks[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newTracks: List<TrackUiModel>) {
        this.tracks = newTracks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tracks.size

    inner class WorkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TrackElementListBinding.bind(view)

        fun onBind(data: TrackUiModel) {

            binding.name.text = data.name
            binding.artistName.text = data.artistName
            binding.duration.progress = data.duration!!.toInt()
            data.albumImage?.let { binding.albumImage.load(it) }

            binding.albumImage.setOnClickListener {
                listenerAlbumImage.invoke()
            }

            binding.maskGroup.setOnClickListener {
                listenerPosition.invoke(adapterPosition)
            }

            binding.artistName.setOnClickListener {
                listenerArtistName.invoke()
            }
        }
    }
}
