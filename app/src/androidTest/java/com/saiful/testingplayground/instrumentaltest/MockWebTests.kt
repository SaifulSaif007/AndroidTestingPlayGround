package com.saiful.testingplayground.instrumentaltest

import com.google.common.truth.Truth.assertThat
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.saiful.testingplayground.instrumentaltest.service.NetworkService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MockWebTests {
    private lateinit var server: MockWebServer
    private lateinit var service: NetworkService
    private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource

    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder().baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)

    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName: String,
        responseCode: Int = 200,
    ) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(responseCode)
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @Test
    fun test_posts_with_success_response() {
        runBlocking {
            enqueueMockResponse("posts.json")
            val responseBody = service.getPost()
            assertThat(responseBody[0].id).isEqualTo(1)
            assertThat(responseBody).isNotNull()

        }
    }

    @Test
    fun test_comment_success_response() {
        runBlocking {
            enqueueMockResponse("comments.json")
            val responseBody = service.getPostComments(id = 1)
            assertThat(responseBody[0].id).isEqualTo(1)
            assertThat(responseBody).isNotNull()
        }
    }

    @Test
    fun test_right_api_call() {
        runBlocking {
            enqueueMockResponse("posts.json")
            service.getPost()
            val request = server.takeRequest()
            assertThat(request.path).isEqualTo("/posts")
        }
    }
}