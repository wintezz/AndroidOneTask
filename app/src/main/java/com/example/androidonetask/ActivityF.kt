package com.example.androidonetask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityFBinding

class ActivityF : AppCompatActivity() {

    private lateinit var binding: ActivityFBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}