package com.sisiame.openweatherapiapp.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val location: Location,

    @SerializedName("weather")
    val condition: List<WeatherCondition>,

    @SerializedName("main")
    val current: WeatherCurrent
) {
    // Override toString for debugging
    override fun toString(): String {
        return """
            Temperature: ${current.tempF}°F
            Weather Condition: ${condition[0].text}
            Weather Condition Icon URL: https://openweathermap.org/img/wn/${condition[0].icon}@2x.png
            Humidity: ${current.humidity}%
            Pressure: ${current.pressure}
            Feels Like: ${current.feelsLikeF}°F
        """.trimIndent()
    }
}

data class Location(
    @SerializedName("lat")
    val latitude: Double = 0.0,

    @SerializedName("lon")
    val longitude: Double = 0.0,

    @SerializedName("name")
    val name: String
)

data class WeatherCurrent(
    @SerializedName("temp")
    val tempF: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("feels_like")
    val feelsLikeF: Double,

    @SerializedName("pressure")
    val pressure: Double
)

data class WeatherCondition(
    @SerializedName("main")
    val text: String,

    @SerializedName("icon")
    val icon: String
)
