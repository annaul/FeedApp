package com.example.feedapp.network

import com.example.feedapp.models.Page
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageResponse(
    @Json(name = "page")
    val page: Page
)