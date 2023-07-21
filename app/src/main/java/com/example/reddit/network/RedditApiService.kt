package com.example.reddit.network

import com.example.reddit.data.SubRedditPost
import retrofit2.http.GET

interface RedditApiService {
    @GET("all")
    suspend fun getRedditPosts(): List<SubRedditPost>
}