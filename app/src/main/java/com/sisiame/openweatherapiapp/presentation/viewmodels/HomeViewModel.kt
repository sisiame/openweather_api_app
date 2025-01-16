package com.sisiame.openweatherapiapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisiame.openweatherapiapp.data.local.WeatherDataStore
import com.sisiame.openweatherapiapp.data.repositories.WeatherRepository
import com.sisiame.openweatherapiapp.models.WeatherResponse
import com.sisiame.openweatherapiapp.presentation.ui.components.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherDataStore: WeatherDataStore
) : ViewModel() {
    private val _weatherState = MutableLiveData<HomeScreenState>(HomeScreenState.NoCitySelected)
    val weatherState: LiveData<HomeScreenState> get() = _weatherState

    init {
        // Fetch the saved city or set a default city
        viewModelScope.launch {
            getSavedWeatherDetails()
        }
    }

    // Method to search a city and update the UI state
    fun searchCity(city: String, apiKey: String) {
        if (city.isBlank()) {
            // If the city is blank, retrieve the saved city
            getSavedWeatherDetails()
            return
        }

        viewModelScope.launch {
            val response = weatherRepository.fetchWeather(apiKey, city)
            // in the future, we can update this to handle more states
            // with different screens (http error, etc.), but we will
            // keep it simple for the sake of this assignment
            if (response.isSuccess && response.getOrNull()!!.location.name.equals(city, ignoreCase = true)) {
                _weatherState.value = HomeScreenState.CityFound(response.getOrNull()!!)
            } else {
                _weatherState.value = HomeScreenState.CityNotFound
            }
        }
    }

    // Method to search a city by location
    fun getCurrentLocationWeather(lat: Double, lon: Double, apiKey: String) {
        viewModelScope.launch {
            val response = weatherRepository.fetchWeatherByCoordinate(apiKey, lat, lon)
            // in the future, we can update this to handle more states
            // with different screens (http error, etc.), but we will
            // keep it simple for the sake of this assignment
            if (response.isSuccess) {
                selectCity(response.getOrNull()!!)
            } else {
                _weatherState.value = HomeScreenState.CityNotFound
            }
        }
    }

    // Method to select a city from the search results
    fun selectCity(weatherResponse: WeatherResponse) {
        _weatherState.value = HomeScreenState.CitySelected(weatherResponse)
        // saves selected city to local datastore
        saveCityToDataStore(weatherResponse)
    }

    private fun saveCityToDataStore(weatherResponse: WeatherResponse) {
        viewModelScope.launch {
            weatherDataStore.saveWeatherDetails(weatherResponse)  // Call to save the city in DataStore
        }
    }

    private fun getSavedWeatherDetails() {
        viewModelScope.launch {
            // Retrieve the saved weather data from DataStore
            val savedWeatherData = weatherDataStore.getWeatherDetails().first()

            // Check if the saved weather data is valid
            if (savedWeatherData.location.name.isNotBlank()) {
                // If valid data exists, set the state to CitySelected
                // populate screen with saved weather data
                _weatherState.value = HomeScreenState.CitySelected(savedWeatherData)
            } else {
                // If no valid data exists, set the state to NoCitySelected
                _weatherState.value = HomeScreenState.NoCitySelected
            }
        }
    }
}