package com.example.akarapodcast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.akarapodcast.databinding.ViewHolderPodcastVboxBinding
import com.example.akarapodcast.model.api.model.entities.Podcast
import com.squareup.picasso.Picasso
import java.io.IOException

class PodcastsAdapter() : ListAdapter<Podcast, PodcastsAdapter.PodcastViewHolder>(object :
    DiffUtil.ItemCallback<Podcast>() {
    override fun areItemsTheSame(oldItem: Podcast, newItem: Podcast): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Podcast, newItem: Podcast): Boolean {
        return oldItem.id == newItem.id
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewHolderPodcastVboxBinding =
            ViewHolderPodcastVboxBinding.inflate(layoutInflater, parent, false)

        return PodcastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val item: Podcast = getItem(position)
        try {
            holder.bind(item)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    class PodcastViewHolder(private var itemBinding: ViewHolderPodcastVboxBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(podcast: Podcast) {
            // bind image to recycle view
            Picasso.get().load(podcast.imageUrl).into(itemBinding.imgPodcast)

            // bind podcaster to recycle view
            itemBinding.podcaster.text = podcast.podcaster

            // bind podcast title to recycle view
            itemBinding.title.text = podcast.title
        }
    }
}