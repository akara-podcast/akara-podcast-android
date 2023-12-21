package com.example.akarapodcast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.akarapodcast.model.api.model.ApiData
import com.example.akarapodcast.model.api.model.Podcast
import com.example.akarapodcast.model.api.model.Status
import com.example.akarapodcast.model.api.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("NAME_SHADOWING")
class PodcastsViewModel {

    // LIVE DATA
    private val _podcastsData = MutableLiveData<ApiData<List<Podcast>>>()
    val podcastData: LiveData<ApiData<List<Podcast>>>
        get() = _podcastsData

    fun loadPodcasts() {
        // loading
        val apiData = ApiData<List<Podcast>>(Status.PROCESSING, null)
        _podcastsData.postValue(apiData)

        // create retrofit client
        val httpClient = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service Object
        val apiService = httpClient.create(ApiService::class.java)

        // Load Podcast List from server
        val task = apiService.getPodcastList()
        task.enqueue(object : Callback<List<Podcast>>{
            override fun onResponse(
                call: Call<List<Podcast>>,
                response: Response<List<Podcast>>
            ) {
                if (response.isSuccessful){
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _podcastsData.postValue(apiData)
                } else {
                    val apiData = ApiData<List<Podcast>>(Status.ERROR, null)
                    _podcastsData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<List<Podcast>>, t: Throwable) {
                val apiData = ApiData<List<Podcast>>(Status.ERROR, null)
                _podcastsData.postValue(apiData)
            }

        })


    }

}