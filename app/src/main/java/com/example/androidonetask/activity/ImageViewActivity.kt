package com.example.androidonetask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityImageViewBinding

class ImageViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = this.javaClass.simpleName
    }
}