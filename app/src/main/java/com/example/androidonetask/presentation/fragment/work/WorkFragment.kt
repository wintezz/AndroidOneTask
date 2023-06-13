package com.example.androidonetask.presentation.fragment.work

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.presentation.adapter.DelegateAdapter
import com.example.androidonetask.presentation.adapter.delegates.*
import com.example.androidonetask.presentation.fragment.base.BaseFragment
import com.example.androidonetask.presentation.model.Item
import com.example.androidonetask.presentation.service.MusicService
import com.example.androidonetask.presentation.utils.PaginationScrollListener
import com.example.androidonetask.presentation.utils.VerticalItemDecorator
import com.example.androidonetask.presentation.utils.load
import com.example.androidonetask.presentation.utils.px
import com.example.androidonetask.presentation.viewmodel.work.MusicUiState
import com.example.androidonetask.presentation.viewmodel.work.WorkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class WorkFragment :
    BaseFragment<WorkViewModel, FragmentArtistBinding>(FragmentArtistBinding::inflate), ServiceConnection {

    private var musicService: MusicService? = null

    private val adapter = DelegateAdapter(
        delegates = listOf(
            TrackDelegate(
                onItemClickNameHolder = ::onClickView,
                onItemClickAudioUrl = { audio, albumImage, artistName, trackName, isPlaying ->
                    viewModel.onItemClickAudioUrl(
                        audio,
                        albumImage,
                        artistName,
                        trackName,
                        isPlaying
                    )
                }
            ),
            CardDelegate(),
            ViewPagerDelegate(),
            TitleDelegate(),
            LoaderDelegate(),
            ErrorDelegate(
                onItemClickViewHolder = ::onClickError
            )
        )
    )

    override fun getFragmentView() = R.layout.fragment_artist

    override fun getViewModelClass() = WorkViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName

        initRecyclerView()
        setupObserverTrack()
        scrollRecyclerView()
        onChangeStateSeekBar()
        onClickPrefMediaItemExoplayer()
        onClickNextMediaItemExoplayer()
        onStartForegroundService()
        onStopForegroundService()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MusicBinder
        musicService = binder.currentService()
        musicService?.showNotification()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        musicService = null
    }

    private fun onStartForegroundService() {
        val startIntent = Intent(requireContext(), MusicService::class.java)
        ContextCompat.startForegroundService(requireContext(), startIntent)
    }

    private fun onStopForegroundService() {
        Intent(requireContext(), MusicService::class.java).let { intent ->
            requireContext().stopService(intent)
        }
    }

    private fun onChangeStateSeekBar() {
        binding.bottomLayout.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if (fromUser) {
                    viewModel.exoPlayer.seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                viewModel.shouldUpdateSeekbar = false
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    viewModel.shouldUpdateSeekbar = true
                    binding.bottomLayout.seekBar.progress = it.progress
                }
            }
        })
    }

    private fun onClickStartAndStopExoplayer(time: Long) {
        setCurPlayerTimeToStartTrack(time)
        binding.bottomLayout.seekBar.progress = time.toInt()
        binding.bottomLayout.bottomPlayPauseButton.setOnClickListener {
            viewModel.onStartAndStopCallBacksExoPlayer()
            binding.bottomLayout.bottomPlayPauseButton.setImageResource(
                if (!viewModel.exoPlayer.isPlaying) {
                    R.drawable.ic_play
                } else {
                    R.drawable.ic_pause
                }
            )
        }
    }

    private fun onClickNextMediaItemExoplayer() {
        binding.bottomLayout.bottomButtonSkip.setOnClickListener {
            viewModel.exoPlayer.seekToNextMediaItem()
            setCurPlayerTimeToDurationTrack(viewModel.exoPlayer.duration)
        }
    }

    private fun onClickPrefMediaItemExoplayer() {
        binding.bottomLayout.bottomBottomBack.setOnClickListener {
            viewModel.exoPlayer.seekToPreviousMediaItem()
            setCurPlayerTimeToDurationTrack(viewModel.exoPlayer.duration)
        }
    }

    private fun submitDataExoPlayer(
        artist: String,
        album: String,
        trackName: String
    ) {
        with(binding) {
            bottomLayout.bottomAlbumImage.load(album)
            bottomLayout.bottomArtistName.text = artist
            bottomLayout.bottomTrackName.text = trackName
            bottomLayout.seekBar.max = viewModel.exoPlayer.duration.toInt()
            setCurPlayerTimeToDurationTrack(viewModel.exoPlayer.duration)
        }
    }

    private fun setCurPlayerTimeToStartTrack(ms: Long) {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        binding.bottomLayout.bottomStartTime.text = dateFormat.format(ms)
    }

    private fun setCurPlayerTimeToDurationTrack(ms: Long) {
        val dateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
        binding.bottomLayout.bottomDuration.text = dateFormat.format(ms)
    }

    private fun showContent(music: List<Item>) {
        with(binding) {
            adapter.updateItem(music)
            recView.isVisible = true
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        requireContext().getDrawable(R.drawable.divider_drawable)
            ?.let { VerticalItemDecorator(it, 32.px, listOf(R.layout.track_element_list)) }
            ?.let {
                binding.recView.addItemDecoration(it)
            }
    }

    private fun scrollRecyclerView() {
        binding.recView.addOnScrollListener(object :
            PaginationScrollListener(binding.recView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.nextLoadPage()
            }

            override fun isLastPage() = viewModel.isLastPageLoaded
            override fun isLoading() = viewModel.isLoadingTracks
        })
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_worksFragment_to_artActivity)
    }

    private fun onClickError() {
        viewModel.nextLoadPage()
    }

    private fun setupObserverTrack() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.staticStateMusic.collect { trackState ->
                when (trackState) {
                    is MusicUiState.Success -> showContent(trackState.music)
                    is MusicUiState.DataExoPlayer -> submitDataExoPlayer(
                        trackState.albumImage,
                        trackState.artistName,
                        trackState.trackName
                    )

                    is MusicUiState.TimeSeekBar -> onClickStartAndStopExoplayer(trackState.time)
                }
            }
        }
    }
}