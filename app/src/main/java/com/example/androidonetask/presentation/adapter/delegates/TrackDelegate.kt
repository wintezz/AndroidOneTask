package com.example.androidonetask.presentation.adapter.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.view.isVisible
import com.example.androidonetask.R
import com.example.androidonetask.databinding.TrackElementListBinding
import com.example.androidonetask.presentation.adapter.BaseViewHolder
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.utils.load

class TrackDelegate(
    private var onItemClickViewHolder: () -> Unit = {},
    private var onItemClickNameHolder: () -> Unit = {},
    private var onItemClickPositionHolder: (Int) -> Unit = {},
    private var onItemClickAudioUrl: ((String, String, String, String, Boolean) -> Unit)? = null
) :
    BaseDelegate<TrackDelegate.TrackViewHolder, Item> {

    override val viewTypeViewBinding: Int = R.layout.track_element_list

    override fun equals(other: Any?) =
        (other is TrackDelegate)
                && viewTypeViewBinding == other.viewTypeViewBinding

    override fun hashCode(): Int {
        return viewTypeViewBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return TrackViewHolder(
            TrackElementListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, items: Item) {
        (items as? Item.TrackUiModel)?.let(holder::bind)
    }

    override fun isForViewType(items: Item): Boolean = items is Item.TrackUiModel

    inner class TrackViewHolder(private val binding: TrackElementListBinding) :
        BaseViewHolder(binding.root) {

        private var shouldUpdateSeekbar = true

        fun bind(item: Item.TrackUiModel) {
            binding.name.text = item.name
            binding.artistName.text = item.artistName

            item.duration?.let { binding.duration.max = it.toInt() }
            item.albumImage?.let { binding.albumImage.load(it) }

            binding.imagePlayButton.setOnClickListener {

                binding.imagePlayButton.setImageResource(
                    if (!item.isPlaying) {
                        R.drawable.ic_pause
                    } else {
                        R.drawable.ic_play
                    }
                )

                binding.duration.isVisible = !item.isPlaying

                binding.duration.setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        /*if (fromUser) {
                            viewModel.exoPlayer.seekTo(progress.toLong())
                        }*/
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        shouldUpdateSeekbar = false
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        seekBar?.let {
                            shouldUpdateSeekbar = true
                            binding.duration.progress = it.progress
                        }
                    }
                })

                onItemClickAudioUrl?.invoke(
                    item.audio.toString(),
                    item.artistName.toString(),
                    item.albumImage.toString(),
                    item.name.toString(),
                    item.isPlaying
                )
            }

            binding.albumImage.setOnClickListener {
                onItemClickViewHolder.invoke()
            }

            binding.maskGroup.setOnClickListener {
                onItemClickPositionHolder.invoke(absoluteAdapterPosition)
            }

            binding.artistName.setOnClickListener {
                onItemClickNameHolder.invoke()
            }
        }
    }
}