package com.example.androidonetask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityEBinding

class ActivityE : AppCompatActivity() {

    private lateinit var binding: ActivityEBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonF.setOnClickListener {
            startActivity(Intent(this, ActivityF::class.java))
        }
    }
}