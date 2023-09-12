package com.example.feedapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import com.example.feedapp.models.*

@Composable
fun FeedScreen(parsedCards: MutableList<CardModel>) {
    LazyColumn(contentPadding = PaddingValues(20.dp)) {
        for (i in 0 until parsedCards.size) {
            when (parsedCards[i].cardType) {
                Type.TEXT -> {
                    item {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = (parsedCards[i] as Text).value,
                            color = Color((parsedCards[i] as Text).textColor.toColorInt()),
                            fontSize = (parsedCards[i] as Text).fontSize.sp,
                            textAlign = TextAlign.Center,
                            fontStyle = MaterialTheme.typography.h1.fontStyle,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Type.TITLE_DESCRIPTION -> {
                    item {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = (parsedCards[i] as TitleDecription).titleValue,
                            color = Color((parsedCards[i] as TitleDecription).titleTextColor.toColorInt()),
                            fontSize = (parsedCards[i] as TitleDecription).titleTextFontSize.sp,
                            fontStyle = MaterialTheme.typography.h3.fontStyle,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = (parsedCards[i] as TitleDecription).descriptionValue,
                            color = Color((parsedCards[i] as TitleDecription).descriptionTextColor.toColorInt()),
                            fontSize = (parsedCards[i] as TitleDecription).descriptionFontSize.sp,
                            fontStyle = MaterialTheme.typography.body1.fontStyle,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Type.IMAGE_TITLE_DESCRIPTION -> {
                    item {
                        Box(
                            contentAlignment = Alignment.BottomStart
                        ) {
                            val width = (parsedCards[i] as ImageTitleDescription).ImageWidth.dp
                            val height = (parsedCards[i] as ImageTitleDescription).ImageHeight.dp
                            Image(
                                painter = rememberAsyncImagePainter(
                                    (parsedCards[i] as ImageTitleDescription).ImageUrl
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .aspectRatio(width / height)
                            )
                            Column(modifier = Modifier.padding(15.dp)) {
                                Text(
                                    text = (parsedCards[i] as ImageTitleDescription).titleValue,
                                    color = Color((parsedCards[i] as ImageTitleDescription).titleTextColor.toColorInt()),
                                    fontSize = (parsedCards[i] as ImageTitleDescription).titleTextFontSize.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = (parsedCards[i] as ImageTitleDescription).descriptionValue,
                                    color = Color((parsedCards[i] as ImageTitleDescription).descriptionTextColor.toColorInt()),
                                    fontSize = (parsedCards[i] as ImageTitleDescription).descriptionFontSize.sp,
                                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}