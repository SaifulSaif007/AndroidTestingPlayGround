package com.saiful.testingplayground.instrumentaltest.view

import com.saiful.testingplayground.instrumentaltest.service.RetrofitService

class Repository {

    private val apiService = RetrofitService().service

    suspend fun getPosts() = apiService.getPost()

}