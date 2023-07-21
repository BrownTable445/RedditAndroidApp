package com.example.reddit.ui.theme.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.reddit.RedditApplication
import com.example.reddit.data.RedditPostsRepository
import com.example.reddit.data.SubRedditPost
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface RedditUiState {
    data class Success(val posts: List<SubRedditPost>) : RedditUiState
    object Error : RedditUiState
    object Loading : RedditUiState
}

class RedditViewModel(private val redditPostsRepository: RedditPostsRepository) : ViewModel() {
    var redditUiState: RedditUiState by mutableStateOf(RedditUiState.Loading)
        private set

    init {
        getRedditPosts()
    }

    fun getRedditPosts() {
        viewModelScope.launch {
            redditUiState = RedditUiState.Loading
            redditUiState = try {
                RedditUiState.Success(redditPostsRepository.getRedditPosts())
            } catch (e: IOException) {
                Log.e("MYAPP", "IOexception", e)
                RedditUiState.Error
            } catch (e: HttpException) {
                Log.e("MYAPP", "HTTPexception", e)
                RedditUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RedditApplication)
                val redditPostsRepository = application.container.redditPostRepository
                RedditViewModel(redditPostsRepository = redditPostsRepository)
            }
        }
    }
}