package com.example.akarapodcast.view.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.akarapodcast.R
import com.example.akarapodcast.view.fragment.HomeFragment
import com.example.akarapodcast.databinding.ActivityMainBinding
import com.example.akarapodcast.databinding.FragmentDiscoverBinding
import com.example.akarapodcast.databinding.FragmentPodcastDetailedBinding
import com.example.akarapodcast.model.api.model.entities.Podcast
import com.example.akarapodcast.other.OnDataPass
import com.example.akarapodcast.view.fragment.FavoriteFragment
import com.example.akarapodcast.view.fragment.PlaylistFragment
import com.example.akarapodcast.view.fragment.SearchFragment
import com.example.akarapodcast.view.fragment.TrendingFragment
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), OnDataPass {

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

    override fun onDataPass(
        data: Podcast,
        mediaPlayer: MediaPlayer,
        binding1: FragmentDiscoverBinding?,
        binding2: FragmentPodcastDetailedBinding?
    ) {

        // bind image to recycle view
        Picasso.get().load(data.imageUrl).into(binding.CurPodcastImage)

        binding.ivPlayPause.setImageResource(R.drawable.ic_pause_foreground)
        binding.title.text = data.title
        binding.podcaster.text = data.podcaster

        mediaPlayer.setOnCompletionListener {
            binding.ivPlayPause.setImageResource(R.drawable.ic_play_foreground)

            if (binding1 == null){
                binding2?.playBtn?.setImageResource(R.drawable.ic_play_foreground)
            } else {
                binding1.playPauseBtn.setImageResource(R.drawable.ic_play_foreground)
            }
        }
    }
}