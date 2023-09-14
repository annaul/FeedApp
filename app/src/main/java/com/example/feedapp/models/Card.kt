package com.example.feedapp.models

import com.squareup.moshi.Json

data class Card(
    val card: CardX,
    @Json(name = "card_type")
    val cardType: String
)