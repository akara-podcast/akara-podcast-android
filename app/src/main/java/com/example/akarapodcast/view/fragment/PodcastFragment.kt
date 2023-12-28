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
import com.example.akarapodcast.databinding.FragmentPodcastBinding
import com.example.akarapodcast.model.api.model.entities.Podcast
import com.example.akarapodcast.other.OnDataPass
import com.example.akarapodcast.view.activity.MainActivity
import com.squareup.picasso.Picasso

class PodcastFragment : Fragment() {
    private lateinit var binding: FragmentPodcastBinding

    lateinit var podcast: Podcast

    private val navigateArgs: PodcastDetailedFragmentArgs by navArgs()

    private var isPlaying = false

    private lateinit var dataPasser: OnDataPass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPodcastBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDetail()

        binding.backBtn.setOnClickListener{

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    @SuppressLint("SetTextI18n")
    private fun showDetail() {

        val mediaPLayer = MediaPlayer.create(requireContext(),
            arguments?.getString("podcastUrl")?.toUri()
        )

        // bind image to recycle view
        Picasso.get().load(arguments?.getString("imgUrl")).into(binding.podcastImg)

        // bind podcaster image to recycle view
        Picasso.get().load(arguments?.getString("imgUrl")).into(binding.podcasterImg)

        binding.title.text = arguments?.getString("title")

        binding.categoryDuration.text = arguments?.getString("category")

        binding.description.text = arguments?.getString("description")

        binding.podcaster.text = arguments?.getString("podcaster")

        binding.podcasterHead.text = arguments?.getString("podcaster")



        binding.playBtn.setOnClickListener{
            if (!isPlaying){
                binding.playBtn.setImageResource(R.drawable.ic_pause_foreground)
                isPlaying = true
            } else {
                binding.playBtn.setImageResource(R.drawable.ic_play_foreground)
                isPlaying = false
            }
        }
    }

}