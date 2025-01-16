package com.sisiame.openweatherapiapp.data.repositories

import com.sisiame.openweatherapiapp.data.remote.services.WeatherAPIService
import com.sisiame.openweatherapiapp.models.WeatherResponse
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherAPIService: WeatherAPIService
) {

    suspend fun fetchWeatherByCoordinate(apiKey: String, lat: Double, lon: Double): Result<WeatherResponse> {
        return try {
            val locationResponse = weatherAPIService.getCityByCoordinates(apiKey, lat, lon)
            val locations = locationResponse.body() ?: throw Exception("The city was not found.")
            val location = locations.first()

            val response = weatherAPIService.getCurrentWeather(apiKey, lat, lon)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val responseWithLocation = WeatherResponse(
                        location = location,
                        condition = body.condition,
                        current = body.current
                    )
                    Result.success(responseWithLocation)
                } else {
                    Result.failure(Throwable("Empty response body"))
                }
            } else {
                Result.failure(Throwable("HTTP error: ${response.code()} - ${response.errorBody()?.string()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Throwable("HTTP exception: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Result.failure(Throwable("Unexpected error: ${e.localizedMessage}"))
        }
    }

    suspend fun fetchWeather(apiKey: String, city: String): Result<WeatherResponse> {
        return try {
            val locationResponse = weatherAPIService.getCityByName(apiKey, city)
            val locations = locationResponse.body() ?: throw Exception("The city was not found.")
            val location = locations.first()

            val lat = location.latitude
            val lon = location.longitude

            val response = weatherAPIService.getCurrentWeather(apiKey, lat, lon)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val responseWithLocation = WeatherResponse(
                        location = location,
                        condition = body.condition,
                        current = body.current
                    )
                    Result.success(responseWithLocation)
                } else {
                    Result.failure(Throwable("Empty response body"))
                }
            } else {
                Result.failure(Throwable("HTTP error: ${response.code()} - ${response.errorBody()?.string()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Throwable("HTTP exception: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Result.failure(Throwable("Unexpected error: ${e.localizedMessage}"))
        }
    }
}