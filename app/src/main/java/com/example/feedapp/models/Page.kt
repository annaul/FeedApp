package com.example.feedapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Page(
    @Json(name = "cards")
    val cards: List<Card>
)