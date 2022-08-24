package com.saiful.testingplayground.instrumentaltest.service

import com.saiful.testingplayground.instrumentaltest.model.Post
import retrofit2.http.GET

interface NetworkService {

    @GET("posts")
    suspend fun getPost(): List<Post>

}