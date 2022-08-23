package com.saiful.testingplayground.instrumentaltest.service

import com.saiful.testingplayground.instrumentaltest.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {

    @GET("posts")
    fun getPost(): Call<List<Post>>

}