package com.saiful.testingplayground.instrumentaltest.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.saiful.testingplayground.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainVM
    private lateinit var getBtn: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getBtn = findViewById(R.id.button)
        textView = findViewById(R.id.text_show)

        viewModel = ViewModelProvider(this)[MainVM::class.java]

        getBtn.setOnClickListener {
            viewModel.callApi()
        }

        viewModel.posts.observe(this) {
            textView.text = it[0].title
        }
    }
}