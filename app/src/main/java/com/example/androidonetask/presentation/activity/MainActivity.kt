package com.example.androidonetask.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ActivityMainBinding
import com.example.androidonetask.presentation.service.MusicService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw Throwable("MainActivity binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = this.javaClass.simpleName

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavView?.setupWithNavController(navController)
        binding.navigationDrawer?.setupWithNavController(navController)

        initService()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        stopService()
    }

    private fun initService() {
        Intent(this, MusicService::class.java).also { intent ->
            startService(intent)
        }
    }

    private fun stopService() {
        Intent(this, MusicService::class.java).also { intent ->
            stopService(intent)
        }
    }
}