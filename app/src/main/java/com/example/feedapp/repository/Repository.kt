package com.example.feedapp.repository

import com.example.feedapp.network.ApiClient
import com.example.feedapp.network.PageResponseClass
import retrofit2.Call

class Repository {
    private val client = ApiClient.apiService

    fun getPage(): Call<PageResponseClass> {
        return client.fetchPage("home")
    }
}