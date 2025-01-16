package com.sisiame.openweatherapiapp.data.repositories

import com.sisiame.openweatherapiapp.models.WeatherResponse
import com.sisiame.openweatherapiapp.models.WeatherCurrent
import com.sisiame.openweatherapiapp.models.WeatherCondition
import com.sisiame.openweatherapiapp.models.Location
import kotlinx.coroutines.delay
import javax.inject.Inject

class MockWeatherRepository @Inject constructor() {
    suspend fun fetchWeather(apiKey: String, city: String): Result<WeatherResponse> {
        // Simulating network delay for realism
        delay(1000)

        // Mock data to simulate a successful response
        val mockWeatherResponse = WeatherResponse(
            location = Location(
                name = "London",
                latitude = 0.0,
                longitude = 0.0,
            ),
            condition = listOf(WeatherCondition(
                text = "Patchy light rain",
                icon = "https://openweathermap.org/img/wn/10d@2x.png"
            )),
            current = WeatherCurrent(
                tempF = 39.4,
                humidity = 100,
                feelsLikeF = 38.9,
                pressure = 0.0
            )
        )

        // Simulating a successful result
        return Result.success(mockWeatherResponse)
    }
}