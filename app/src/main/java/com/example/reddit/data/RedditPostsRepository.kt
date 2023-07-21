package com.example.reddit.data

import com.example.reddit.network.RedditApiService

interface RedditPostsRepository {
    suspend fun getRedditPosts(): List<SubRedditPost>
}

class NetworkRedditPostsRepository(private val redditApiService: RedditApiService) : RedditPostsRepository {
    override suspend fun getRedditPosts(): List<SubRedditPost> = redditApiService.getRedditPosts()
}