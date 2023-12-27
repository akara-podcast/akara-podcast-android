package com.example.akarapodcast.other

import android.media.MediaPlayer
import com.example.akarapodcast.databinding.FragmentDiscoverBinding
import com.example.akarapodcast.databinding.FragmentPodcastDetailedBinding
import com.example.akarapodcast.model.api.model.entities.Podcast

interface OnDataPass {
    fun onDataPass(data: Podcast, mediaPlayer: MediaPlayer, binding1: FragmentDiscoverBinding?, binding2: FragmentPodcastDetailedBinding?)
}