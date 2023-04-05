package com.example.androidonetask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidonetask.databinding.ActivityMainBinding
import com.example.androidonetask.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsFragment = NewsFragment()
    private val artistFragment = ArtistFragment()
    private val expositionsFragment = ExpositionsFragment()
    private val infoFragment = InfoFragment()
    private val worksFragment = WorksFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.news -> setCurrentFragment(newsFragment)
                R.id.artist -> setCurrentFragment(artistFragment)
                R.id.expositions -> setCurrentFragment(expositionsFragment)
                R.id.info -> setCurrentFragment(infoFragment)
                R.id.works -> setCurrentFragment(worksFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.hostFragment, fragment)
            addToBackStack(null)
            commit()
        }
}