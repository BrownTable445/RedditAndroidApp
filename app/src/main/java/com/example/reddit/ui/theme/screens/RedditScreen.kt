package com.example.reddit.ui.theme.screens

import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.reddit.ui.theme.RedditTheme

@Composable
fun RedditApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RedditScreen.valueOf(
        backStackEntry?.destination?.route ?: RedditScreen.Home.name
    )

    NavHost(
        navController = navController,
        startDestination = RedditScreen.Home.name,
    ) {
        composable(route = RedditScreen.Home.name) {
            val redditViewModel: RedditViewModel = viewModel(factory = RedditViewModel.Factory)
            HomeScreen(
                navController = navController,
                redditUiState = redditViewModel.redditUiState,
                retryAction = redditViewModel::getRedditPosts
            )
        }
        composable(route = RedditScreen.Inbox.name) {

        }
    }
}

@Preview
@Composable
fun PreviewRedditApp() {
    RedditTheme {
        RedditApp()
    }
}