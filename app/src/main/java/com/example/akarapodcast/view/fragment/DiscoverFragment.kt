package com.example.akarapodcast.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akarapodcast.adapter.PodcastsAdapter
import com.example.akarapodcast.databinding.FragmentDiscoverBinding
import com.example.akarapodcast.model.api.model.Status
import com.example.akarapodcast.viewmodel.PodcastsViewModel

class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding

    private val viewModel = PodcastsViewModel()

    private val adapter = PodcastsAdapter()

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
                Status.PROCESSING -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> adapter.submitList(it.data)
                Status.ERROR -> Toast.makeText(requireContext(), "Error while loading data from server", Toast.LENGTH_LONG).show()
            }
        }

        binding.recycleViewPodcast.adapter = adapter
        binding.recycleViewPodcastTech.adapter = adapter
        binding.recycleViewPodcastComedy.adapter = adapter
        viewModel.loadPodcasts()

    }

}