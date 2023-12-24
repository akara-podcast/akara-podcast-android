package com.example.akarapodcast.model.api.model.remote

import com.example.akarapodcast.model.api.model.entities.Podcast
import com.example.akarapodcast.other.Constants.PODCAST_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class PodcastDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val podcastCollection = firestore.collection(PODCAST_COLLECTION)

    suspend fun getAllPodcasts(): List<Podcast> {
        return try {
            podcastCollection.get().await().toObjects(Podcast::class.java)
        } catch (e: Exception){
            emptyList()
        }
    }

}