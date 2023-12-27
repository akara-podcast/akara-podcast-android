package com.example.akarapodcast.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.akarapodcast.R
import com.example.akarapodcast.databinding.FragmentPodcastDetailedBinding
import com.example.akarapodcast.model.api.model.entities.Podcast
import com.squareup.picasso.Picasso

class PodcastDetailedFragment : Fragment() {
    private lateinit var binding: FragmentPodcastDetailedBinding

    lateinit var podcast: Podcast

    private val navigateArgs: PodcastDetailedFragmentArgs by navArgs()

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


    private fun showDetail(podcast: Podcast) {

        // bind image to recycle view
        Picasso.get().load(podcast.imageUrl).into(binding.podcastImg)

        // bind podcaster image to recycle view
        Picasso.get().load(podcast.imageUrl).into(binding.podcasterImg)

        binding.title.text = podcast.title

        binding.categoryDuration.text = podcast.category

        binding.description.text = podcast.description

        binding.podcaster.text = podcast.podcaster
    }

}