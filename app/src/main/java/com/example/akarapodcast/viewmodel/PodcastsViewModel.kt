package com.example.akarapodcast.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akarapodcast.model.api.client.ApiClient
import com.example.akarapodcast.model.api.model.ApiData
import com.example.akarapodcast.model.api.model.Status
import com.example.akarapodcast.model.api.model.entities.Podcast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PodcastsViewModel : ViewModel() {

    // LIVE DATA
    private val _podcastsData = MutableLiveData<ApiData<List<Podcast>>>()
    val podcastData: LiveData<ApiData<List<Podcast>>>
        get() = _podcastsData

    fun loadPodcasts() {
        // loading
        var apiData = ApiData<List<Podcast>>(Status.PROCESSING, null)
        _podcastsData.postValue(apiData)

        // Load Podcast List from server
        viewModelScope.launch(Dispatchers.IO) {
            apiData = try {
                val response = ApiClient.get().apiService.getPodcastList()
                ApiData(Status.SUCCESS, response)
            } catch (ex: Exception) {
                Log.e("podcast", "Load podcast Error: ${ex.message}")
                ApiData(Status.ERROR, null)
            }

            withContext(Dispatchers.Main.immediate) {
                _podcastsData.postValue(apiData)
            }
        }
    }

    fun showDetail(mediaItem: Podcast, context: Context){
        Toast.makeText(context, mediaItem.title, Toast.LENGTH_LONG).show()
    }
}