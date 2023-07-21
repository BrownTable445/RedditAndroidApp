package com.example.reddit.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubRedditPost( //should add date and videoUrl options later, but those will be complicated
    val id: Int,
    val postName: String,
    val description: String,
    val iconURL: String,
    val subRedditName: String,
    val imageUrl: String?,
    val netVoteCount: Int,
)
