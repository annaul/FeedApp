package com.example.feedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.feedapp.ui.FeedScreen
import com.example.feedapp.ui.theme.FeedAppTheme
import com.example.feedapp.viewmodel.PageViewModel

class MainActivity : ComponentActivity() {
    private val pageViewModel : PageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeedAppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    FeedScreen(pageViewModel)
                }
            }
        }
    }
}