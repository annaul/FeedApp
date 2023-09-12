package com.example.feedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.feedapp.models.*
import com.example.feedapp.ui.FeedScreen
import com.example.feedapp.ui.theme.FeedAppTheme
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val jsonString = application.assets
                .open("Repository.json")
                .bufferedReader()
                .use { it.readText() }

            val data = JSONObject(jsonString)
            val page = data.getJSONObject("page")
            val cards = page.getJSONArray("cards")

            val parsedCards = mapCardsToComposeProps(cards)

            FeedAppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    FeedScreen(parsedCards)
                }
            }
        }
    }
}