package com.saiful.testingplayground.instrumentaltest.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiful.testingplayground.instrumentaltest.model.Post
import kotlinx.coroutines.launch

class MainVM : ViewModel() {

    val posts = MutableLiveData<List<Post>>()

    fun callApi() {
        viewModelScope.launch {
            posts.value = Repository().getPosts()
        }
    }
}