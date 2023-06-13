package com.example.androidonetask.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityImageViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageViewActivity : AppCompatActivity() {

    private var _binding: ActivityImageViewBinding? = null
    private val binding
        get() = _binding ?: throw Throwable("ImageViewActivity binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = this.javaClass.simpleName
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}