package com.example.akarapodcast.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.akarapodcast.R
import com.example.akarapodcast.view.fragment.HomeFragment
import com.example.akarapodcast.databinding.ActivityMainBinding
import com.example.akarapodcast.view.fragment.FavoriteFragment
import com.example.akarapodcast.view.fragment.PlaylistFragment
import com.example.akarapodcast.view.fragment.SearchFragment
import com.example.akarapodcast.view.fragment.TrendingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // show DiscoverFragment
        showFragment(HomeFragment())

        // select from nav
        binding.bottomNav.setOnItemSelectedListener {it ->
            when(it.itemId){
                R.id.menuHome -> showFragment(HomeFragment())
                R.id.menuTrending -> showFragment(TrendingFragment())
                R.id.menuSearch -> showFragment(SearchFragment())
                R.id.menuFav -> showFragment(FavoriteFragment())
                R.id.menuPlaylist -> showFragment(PlaylistFragment())
            }

            false
        }
    }

    private fun showFragment(fragment: Fragment) {

        // FragmentManager
        val fragmentManager = supportFragmentManager

        // FragmentTransaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace HomeFragment in lytFragment
        fragmentTransaction.replace(binding.lytFragment.id, fragment)

        // Commit transaction
        fragmentTransaction.commit()
    }
}