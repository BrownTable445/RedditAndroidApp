package com.example.reddit

import android.app.Application
import com.example.reddit.data.AppContainer
import com.example.reddit.data.DefaultAppContainer

class RedditApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}