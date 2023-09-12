package com.example.feedapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import com.example.feedapp.models.ImageTitleDescription
import com.example.feedapp.models.Text
import com.example.feedapp.models.TitleDecription
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
            val cardText = mutableListOf<Text>()
            val cardTitleDescription = mutableListOf<TitleDecription>()
            val cardImageDescription = mutableListOf<ImageTitleDescription>()

            for (i in 0 until cards.length()) {
                val cardType = cards.getJSONObject(i).get("card_type")
                val card = cards.getJSONObject(i).getJSONObject("card")
                if (cardType == "text") {
                    val element = Text(
                        value = card.get("value") as String,
                        textColor = card.getJSONObject("attributes").get("text_color") as String,
                        fontSize = card.getJSONObject("attributes").getJSONObject("font")
                            .get("size") as Int
                    )
                    cardText.add(element)
                } else if (cardType == "title_description") {
                    val element = TitleDecription(
                        titleValue = card.getJSONObject("title").get("value") as String,
                        titleTextColor = card.getJSONObject("title").getJSONObject("attributes")
                            .get("text_color") as String,
                        titleTextFontSize = card.getJSONObject("title").getJSONObject("attributes")
                            .getJSONObject("font").get("size") as Int,
                        descriptionValue = card.getJSONObject("description").get("value") as String,
                        descriptionTextColor = card.getJSONObject("description")
                            .getJSONObject("attributes").get("text_color") as String,
                        descriptionFontSize = card.getJSONObject("description")
                            .getJSONObject("attributes").getJSONObject("font")
                            .get("size") as Int
                    )
                    cardTitleDescription.add(element)
                } else if (cardType == "image_title_description") {
                    val element = ImageTitleDescription(
                        ImageUrl = card.getJSONObject("image").get("url") as String,
                        ImageWidth = card.getJSONObject("image").getJSONObject("size")
                            .get("width") as Int,
                        ImageHeight = card.getJSONObject("image").getJSONObject("size")
                            .get("height") as Int,
                        titleValue = card.getJSONObject("title").get("value") as String,
                    )
                    cardImageDescription.add(element)
                }
            }

            FeedAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn() {
                        items(cardText.size) {
                            for (i in 0 until cardText.size) {
                                Text(
                                    text = cardText[i].value,
                                    color = Color(cardText[i].textColor.toColorInt()),
                                    fontSize = cardText[i].fontSize.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        items(cardTitleDescription.size) {
                            for (i in 0 until cardTitleDescription.size) {
                                Text(
                                    text = cardTitleDescription[i].titleValue,
                                    color = Color(cardTitleDescription[i].titleTextColor.toColorInt()),
                                    fontSize = cardTitleDescription[i].titleTextFontSize.sp,
                                )
                                Text(
                                    text = cardTitleDescription[i].descriptionValue,
                                    color = Color(cardTitleDescription[i].descriptionTextColor.toColorInt()),
                                    fontSize = cardTitleDescription[i].descriptionFontSize.sp,
                                )
                            }
                        }
                        items(cardImageDescription.size) {
                            for (i in 0 until cardImageDescription.size) {
                                Image(
                                    painter = rememberAsyncImagePainter(cardImageDescription[i].ImageUrl),
                                    contentDescription = null,
                                    modifier = Modifier.size(
                                        width = cardImageDescription[i].ImageWidth.dp,
                                        height = cardImageDescription[i].ImageHeight.dp
                                    ),
                                    contentScale = ContentScale.FillWidth
                                )
                                Text(
                                    text = cardImageDescription[i].titleValue
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FeedAppTheme {
        Greeting("Android")
    }
}