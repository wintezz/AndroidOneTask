package com.example.androidonetask.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidonetask.R
import com.example.androidonetask.databinding.ActivityMainBinding
import com.example.androidonetask.fragment.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val newsFragment = NewsFragment()
    private val artistFragment = ArtistFragment()
    private val expositionsFragment = ExpositionsFragment()
    private val infoFragment = InfoFragment()
    private val worksFragment = WorksFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = this.javaClass.simpleName

        binding.navigationDrawer?.setNavigationItemSelectedListener {
            navigateTo(it)
            true
        }

        binding.bottomNavView?.setOnItemSelectedListener {
            navigateTo(it)
            true
        }
    }

    private fun navigateTo(it: MenuItem) {
        when (it.itemId) {
            R.id.news -> setCurrentFragment(newsFragment)
            R.id.artist -> setCurrentFragment(artistFragment)
            R.id.expositions -> setCurrentFragment(expositionsFragment)
            R.id.info -> setCurrentFragment(infoFragment)
            R.id.works -> setCurrentFragment(worksFragment)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}