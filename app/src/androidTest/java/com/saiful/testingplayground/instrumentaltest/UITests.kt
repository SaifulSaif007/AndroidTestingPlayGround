package com.saiful.testingplayground.instrumentaltest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.saiful.testingplayground.R
import com.saiful.testingplayground.instrumentaltest.service.NetworkService
import com.saiful.testingplayground.instrumentaltest.view.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
@LargeTest
class UITests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun successResponse() {
        onView(withId(R.id.button)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.text_show)).check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))
    }

}