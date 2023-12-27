package com.example.akarapodcast.model.api.service

import com.example.akarapodcast.model.api.model.entities.Podcast
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("Parameow3/Parameow3/main/podcast.json")
    suspend fun getPodcastList(): List<Podcast>

    @GET("Parameow3/Parameow3/main/release.json")
    suspend fun getNewRelease(): Podcast
}