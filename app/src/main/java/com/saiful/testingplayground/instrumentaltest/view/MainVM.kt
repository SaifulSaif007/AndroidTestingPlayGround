package com.saiful.testingplayground.instrumentaltest.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saiful.testingplayground.instrumentaltest.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainVM : ViewModel() {

    val posts = MutableLiveData<List<Post>>()

    init {
        callApi()
    }

    private fun callApi() {
        val call = Repository().getposts()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    posts.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("res", t.localizedMessage)
            }

        })
    }
}