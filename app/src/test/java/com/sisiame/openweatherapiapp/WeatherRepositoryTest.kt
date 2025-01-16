package com.sisiame.openweatherapiapp

import com.sisiame.openweatherapiapp.data.remote.services.WeatherAPIService
import com.sisiame.openweatherapiapp.data.repositories.MockWeatherRepository
import com.sisiame.openweatherapiapp.data.repositories.WeatherRepository
import com.sisiame.openweatherapiapp.models.Location
import com.sisiame.openweatherapiapp.models.WeatherCondition
import com.sisiame.openweatherapiapp.models.WeatherCurrent
import com.sisiame.openweatherapiapp.models.WeatherResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class WeatherRepositoryTest {

    private lateinit var weatherAPIService: WeatherAPIService
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        weatherAPIService = Mockito.mock(WeatherAPIService::class.java)
        weatherRepository = WeatherRepository(weatherAPIService)
    }

    @Test
    fun `test fetchWeather success`() = runBlocking {
        // Arrange: Mock data for the weather response
        val mockWeatherResponse = WeatherResponse(
            location = Location(0.0, 0.0, "London"),
            condition = listOf(WeatherCondition("Patchy light rain", "https://openweathermap.org/img/wn/10d@2x.png")),
            current = WeatherCurrent(
                tempF = 39.4,
                humidity = 100,
                feelsLikeF = 38.9,
                pressure = 0.0
            )
        )

        // Mocking the API response
        val mockResponse = Response.success(mockWeatherResponse)

        // Mocking the weatherAPIService to return the mocked response when called
        Mockito.`when`(weatherAPIService.getCurrentWeather(
            Mockito.anyString(),
            Mockito.anyDouble(),
            Mockito.anyDouble(),
            Mockito.anyString()
        )).thenReturn(mockResponse)

        // Act: Call the repository's fetchWeather method
        val result = MockWeatherRepository().fetchWeather("fake_api_key", "London")

        // Assert: Check if the returned result is equal to the mocked response
        assertEquals(mockWeatherResponse, result.getOrNull())
    }

    @Test
    fun `test fetchWeather failure`() = runBlocking {
        val mockErrorResponse = Response.error<WeatherResponse>(403, "Error".toResponseBody(null))
        Mockito.`when`(weatherAPIService.getCurrentWeather(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyString()))
            .thenReturn(mockErrorResponse)

        val result = weatherRepository.fetchWeather("fake_api_key", "London")

        assert(result.isFailure) { "Expected failure response" }
    }

    @Test
    fun `test fetchWeather exception handling`(): Unit = runBlocking {
        Mockito.`when`(weatherAPIService.getCurrentWeather(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyString()))
            .thenThrow(RuntimeException("Network error"))

        val result = weatherRepository.fetchWeather("fake_api_key", "London")

        assertTrue(result.isFailure)

        val exception = result.exceptionOrNull()
        assertNotNull(exception)
    }
}