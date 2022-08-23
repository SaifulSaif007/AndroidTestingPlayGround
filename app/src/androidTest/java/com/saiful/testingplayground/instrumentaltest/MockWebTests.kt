package com.saiful.testingplayground.instrumentaltest

import androidx.test.espresso.IdlingRegistry
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
    fun tearDown(){
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName: String,
        responseCode: Int = 200,
    ) {
        javaClass.classLoader?.let {
            //val inputStream = it.getResourceAsStream(fileName)
           // val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(responseCode)
            mockResponse.setBody(FileReader.readStringFromFile(fileName))
            server.enqueue(mockResponse)
        }
    }

    @Test
    fun testSuccessResponse(){
//        server.dispatcher = object : okhttp3.mockwebserver.Dispatcher(){
//            override fun dispatch(request: RecordedRequest): MockResponse {
//                return MockResponse()
//                    .setResponseCode(200)
//                    .setBody(FileReader.readStringFromFile("posts.json"))
//            }
//
//        }
        runBlocking {
            enqueueMockResponse("posts.json")
            val responseBody = service.getPost()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()

        }
    }
}