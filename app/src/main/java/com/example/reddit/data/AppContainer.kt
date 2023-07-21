package com.example.reddit.data

import com.example.reddit.network.RedditApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val redditPostRepository: RedditPostsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:8080/Reddit/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: RedditApiService by lazy {
        retrofit.create(RedditApiService::class.java)
    }

    override val redditPostRepository: RedditPostsRepository by lazy {
        NetworkRedditPostsRepository(retrofitService)
    }
}