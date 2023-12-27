package com.example.akarapodcast.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.akarapodcast.R
import com.example.akarapodcast.databinding.FragmentPodcastDetailedBinding
import com.example.akarapodcast.model.api.model.entities.Podcast
import com.example.akarapodcast.other.OnDataPass
import com.squareup.picasso.Picasso
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

class PodcastDetailedFragment : Fragment() {
    private lateinit var binding: FragmentPodcastDetailedBinding

    lateinit var podcast: Podcast

    private val navigateArgs: PodcastDetailedFragmentArgs by navArgs()

    private var isPlaying = false

    private lateinit var dataPasser: OnDataPass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPodcastDetailedBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // checking for details passed from list fragment
        podcast = navigateArgs.podcast
        showDetail(podcast)

        binding.backBtn.setOnClickListener{
            findNavController().navigate(R.id.action_podcastDetailedFragment_to_discoverFragment)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    @SuppressLint("SetTextI18n")
    private fun showDetail(podcast: Podcast) {

        val mediaPLayer = MediaPlayer.create(requireContext(), podcast.podcastUrl.toUri())

        // bind image to recycle view
        Picasso.get().load(podcast.imageUrl).into(binding.podcastImg)

        // bind podcaster image to recycle view
        Picasso.get().load(podcast.imageUrl).into(binding.podcasterImg)

        binding.title.text = podcast.title

        binding.categoryDuration.text = podcast.category +" • "+  mediaPLayer.duration/3600 + " mins"

        binding.description.text = podcast.description

        binding.podcaster.text = podcast.podcaster



        binding.playBtn.setOnClickListener{
            if (!isPlaying){
                // pass data
                dataPasser.onDataPass(podcast, mediaPLayer, null, binding)

                mediaPLayer.duration
                mediaPLayer.start()
                binding.playBtn.setImageResource(R.drawable.ic_pause_foreground)
                isPlaying = true
            } else {
                mediaPLayer.pause()
                binding.playBtn.setImageResource(R.drawable.ic_play_foreground)
                isPlaying = false
            }
        }
    }

}