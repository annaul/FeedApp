package com.example.feedapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedapp.network.PageResponse
import com.example.feedapp.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call

open class PageViewModel : ViewModel() {
    private val repository = Repository()

    var state = MutableStateFlow<PageState>(PageState.None)

    init {
        fetchPage()
    }

    fun fetchPage() {
        viewModelScope.launch {
            repository.getPage().enqueue(object : retrofit2.Callback<PageResponse> {
                override fun onResponse(
                    call: Call<PageResponse>,
                    response: retrofit2.Response<PageResponse>
                ) {
                    if (response.isSuccessful) {
                        // I am used to doing the update through a reducer, this is awkward because
                        // I was trying to not use a reducer this time.
                        state.value =
                            response.body()?.page?.let { PageState.Content(it) } ?: PageState.None
                    }
                }

                override fun onFailure(call: Call<PageResponse>, t: Throwable) {
                    Log.e("error", "$t")
                }
            })
        }
    }
}