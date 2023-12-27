package com.example.akarapodcast.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akarapodcast.R
import com.example.akarapodcast.adapter.PodcastsAdapter
import com.example.akarapodcast.databinding.FragmentDiscoverBinding
import com.example.akarapodcast.model.api.model.Status
import com.example.akarapodcast.model.api.model.entities.Podcast
import com.example.akarapodcast.other.OnDataPass
import com.example.akarapodcast.viewmodel.PodcastsViewModel
import com.squareup.picasso.Picasso


class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding

    private val viewModel = PodcastsViewModel()

    private val adapter = PodcastsAdapter()

    private var isPlaying = false

    private lateinit var dataPasser: OnDataPass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup recycler view
        //// create layout manager
        val layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerTech = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerComedy = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewPodcast.layoutManager = layoutManagerPopular
        binding.recycleViewPodcastTech.layoutManager = layoutManagerTech
        binding.recycleViewPodcastComedy.layoutManager = layoutManagerComedy

        // setup observer
        viewModel.podcastData.observe(viewLifecycleOwner) {

            when (it.status){
                Status.PROCESSING -> binding.discoverProgress.isVisible = true
                Status.SUCCESS -> {
                    binding.discoverProgress.isVisible = false
                    adapter.submitList(it.data)
                    it.data?.let { it1 -> showRelease(it1[0]) }
                }
                Status.ERROR -> {
                    binding.discoverProgress.isVisible = false
                    Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
                }
            }
        }



        binding.recycleViewPodcast.adapter = adapter
        binding.recycleViewPodcastTech.adapter = adapter
        binding.recycleViewPodcastComedy.adapter = adapter
        viewModel.loadPodcasts()

        adapter.onClickListener = {
            // create bundle to pass to next fragment
            val bundle = Bundle()
            bundle.putParcelable("podcast", it)
            findNavController().navigate(R.id.action_discoverFragment_to_podcastDetailedFragment, bundle)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    @SuppressLint("SetTextI18n")
    private fun showRelease(podcast: Podcast) {
        val mediaPLayer = MediaPlayer.create(requireContext(), podcast.podcastUrl.toUri())

        // bind image to recycle view
        Picasso.get().load(podcast.imageUrl).into(binding.podcastImg)

        // bind podcaster image to recycle view
        Picasso.get().load(podcast.imageUrl).into(binding.podcasterImg)

        binding.title.text = podcast.title

        binding.category.text = podcast.category +" • "+  mediaPLayer.duration/3600 + " mins"

        binding.podcasterName.text = podcast.podcaster

        binding.playPauseBtn.setOnClickListener{
            if (!isPlaying){
                // pass data
                dataPasser.onDataPass(podcast, mediaPLayer, binding, null)

                mediaPLayer.start()
                binding.playPauseBtn.setImageResource(R.drawable.ic_pause_foreground)
                isPlaying = true
            } else {
                mediaPLayer.pause()
                binding.playPauseBtn.setImageResource(R.drawable.ic_play_foreground)
                isPlaying = false
            }
        }
    }

}