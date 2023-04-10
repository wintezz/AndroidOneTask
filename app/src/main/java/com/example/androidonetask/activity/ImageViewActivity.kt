package com.example.androidonetask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityImageViewBinding
import com.example.androidonetask.utils.Contract

class ImageViewActivity : AppCompatActivity() {

    private var _binding: ActivityImageViewBinding? = null
    private val binding get() = _binding!!
    private val getMessage = registerForActivityResult(Contract()) {
        binding.message.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMessage.launch(binding.message.text)

        title = this.javaClass.simpleName
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}