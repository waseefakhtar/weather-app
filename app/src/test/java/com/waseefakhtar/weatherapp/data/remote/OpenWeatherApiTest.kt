package com.waseefakhtar.weatherapp.data.remote

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherApiTest {

    private lateinit var api: OpenWeatherApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Should get api respose successfully`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{ \"result\":\"success\" }")
        )

        runBlocking {
            val cityInfo = api.getCityInfo()

            assertThat(cityInfo).isNotNull()
        }
    }
}