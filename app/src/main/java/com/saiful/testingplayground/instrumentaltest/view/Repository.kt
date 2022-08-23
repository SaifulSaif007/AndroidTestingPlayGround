package com.saiful.testingplayground.instrumentaltest.view

import com.saiful.testingplayground.instrumentaltest.service.RetrofitService

class Repository {

    private val apiService = RetrofitService().service

    fun getposts() = apiService.getPost()
}