package com.example.feedapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedapp.network.PageResponseClass
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
            repository.getPage().enqueue(object : retrofit2.Callback<PageResponseClass> {
                override fun onResponse(
                    call: Call<PageResponseClass>,
                    response: retrofit2.Response<PageResponseClass>
                ) {
                    if (response.isSuccessful) {
                        state.value =
                            response.body()?.page?.let { PageState.Content(it) } ?: PageState.None
                    }
                }

                override fun onFailure(call: Call<PageResponseClass>, t: Throwable) {
                    Log.e("error", "$t")
                }
            })
        }
    }
}