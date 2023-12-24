package com.example.akarapodcast.model.api.model.entities

import com.google.gson.annotations.SerializedName

data class Podcast (
    @SerializedName("id")
    val id: String,
    @SerializedName("podcastTitle")
    val title: String,
    @SerializedName("podcastDescription")
    val description: String,
    @SerializedName("podcaster")
    val podcaster: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("podcastUrl")
    val podcastUrl: String
)