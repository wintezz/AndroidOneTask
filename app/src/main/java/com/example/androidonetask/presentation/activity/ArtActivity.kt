package com.example.androidonetask.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityArtBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtActivity : AppCompatActivity() {

    private var _binding: ActivityArtBinding? = null
    private val binding get() = _binding ?: throw Throwable("ArtActivity binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = this.javaClass.simpleName
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}