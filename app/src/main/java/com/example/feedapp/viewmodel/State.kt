package com.example.feedapp.viewmodel

import com.example.feedapp.models.Page

sealed class PageState {
    object None : PageState()
    data class Content(
        val page: Page
    ) : PageState()
}