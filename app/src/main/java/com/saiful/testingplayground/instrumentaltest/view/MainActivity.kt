package com.saiful.testingplayground.instrumentaltest.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.saiful.testingplayground.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainVM::class.java]

        viewModel.posts.observe(this) {
            Log.d("posts", it.toString())
            findViewById<TextView>(R.id.text_show).text = it[0].title
        }
    }
}