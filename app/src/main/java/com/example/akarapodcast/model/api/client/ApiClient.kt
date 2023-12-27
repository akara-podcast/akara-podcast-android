package com.example.akarapodcast.model.api.client

import com.example.akarapodcast.model.api.service.ApiService
import com.example.akarapodcast.other.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor(){

    // create retrofit client
    private val httpClient = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create Service Object
    val apiService = httpClient.create(ApiService::class.java)
    companion object {

        private var instance: ApiClient? = null

        fun get(): ApiClient {
            if (instance == null){
                instance = ApiClient()
            }

            return instance!!
        }
    }

}