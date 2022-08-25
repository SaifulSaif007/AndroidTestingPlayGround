package com.saiful.testingplayground.instrumentaltest.service

import com.saiful.testingplayground.instrumentaltest.model.Comments
import com.saiful.testingplayground.instrumentaltest.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("posts")
    suspend fun getPost(): List<Post>


    @GET("posts/{post_id}/comments")
    suspend fun getPostComments(
        @Path("post_id") id: Int
    ): List<Comments>
}