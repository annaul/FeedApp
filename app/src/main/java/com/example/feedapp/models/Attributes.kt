package com.example.feedapp.models

import com.squareup.moshi.Json

data class Attributes(
    val font: Font,
    @Json(name = "text_color")
    val textColor: String
)