package com.example.akarapodcast

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.akarapodcast.R.id.discover
import com.example.akarapodcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // show DiscoverFragment
        showFragment(DiscoverFragment())
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