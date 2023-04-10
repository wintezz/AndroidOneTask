package com.example.androidonetask.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidonetask.databinding.ActivityArtBinding

class ArtActivity : AppCompatActivity() {

    private var _binding: ActivityArtBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sendResult()

        title = this.javaClass.simpleName
    }

    private fun sendResult() {
        val data = Intent().putExtra("message", binding.message.text as CharSequence)
        setResult(RESULT_OK, data)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}