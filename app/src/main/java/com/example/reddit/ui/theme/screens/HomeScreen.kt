package com.example.reddit.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reddit.data.SubRedditPost
import com.example.reddit.ui.theme.RedditTheme

enum class RedditScreen() {
    Home,
    Inbox
}

@Composable
fun HomeScreen(
    navController: NavController,
    redditUiState: RedditUiState,
    retryAction: () -> Unit
) {
    when (redditUiState) {
        is RedditUiState.Loading -> LoadingScreen()
        is RedditUiState.Success -> SuccessScreen(
            navController = navController,
            subRedditPosts = redditUiState.posts,
            retryAction = {}
        )
        else -> ErrorScreen()
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    RedditTheme {
        val redditViewModel: RedditViewModel = viewModel(factory = RedditViewModel.Factory)
        HomeScreen(
            navController = rememberNavController(),
            redditUiState = redditViewModel.redditUiState,
            retryAction = {}
        )
    }
}

@Composable
fun SubRedditPostColumn(
    subRedditPosts: List<SubRedditPost>,
    modifier: Modifier = Modifier,
) {
    LazyColumn() {
        items(subRedditPosts) {
            SubRedditPostBlock(
                subRedditPost = it
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Text(
        text = "Loading"
    )
}

@Composable
fun ErrorScreen() {
    Text(
        text = "error"
    )
}

@Composable
fun SuccessScreen(
    navController: NavController = rememberNavController(),
    subRedditPosts: List<SubRedditPost>,
    retryAction: () -> Unit
) {
    Scaffold(
        bottomBar = {RedditBottomBar(navController = navController)}
    ) {
        SubRedditPostColumn(
            subRedditPosts = subRedditPosts,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun RedditBottomBar(navController: NavController) {
    BottomNavigation(
        contentColor = Color.White,
        backgroundColor = Color.Black
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.navigate(RedditScreen.Home.name)
            },
            icon = {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.navigate(RedditScreen.Inbox.name)
            },
            icon = {
                Icon(
                    Icons.Rounded.Notifications,
                    contentDescription = null
                )
            }
        )
    }
}

