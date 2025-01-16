package com.sisiame.openweatherapiapp.data.remote.services

import com.sisiame.openweatherapiapp.models.Location
import com.sisiame.openweatherapiapp.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    /**
     * Get the current weather details from the queried city.
     *
     * @param apiKey The API key provided by openweathermap.org.
     * @param lat The latitude of the city being queried.
     * @param lon The longitude of the city being queried.
     *
     * @return The response from the open weather API.
     */
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("appid") apiKey: String,
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("units") units: String = "imperial"
    ) : Response<WeatherResponse>

    @GET("geo/1.0/direct")
    suspend fun getCityByName(
        @Query("appid") apiKey: String,
        @Query("q") city: String,
    ) : Response<List<Location>>

    @GET("geo/1.0/reverse")
    suspend fun getCityByCoordinates(
        @Query("appid") apiKey: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ) : Response<List<Location>>
}