package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.local.AppDatabase
import com.example.weather.model.FullWeatherUiState
import com.example.weather.model.LocationItem
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class TemperatureUnit {
    CELSIUS, FAHRENHEIT
}

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val data: FullWeatherUiState) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val repository = WeatherRepository(weatherDao = db.weatherDao())

    val favoriteLocations: StateFlow<List<LocationItem>> = repository.favoriteLocations
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = repository.defaultLocations
        )

    private val _selectedLocation = MutableStateFlow(repository.defaultLocations.first())
    val selectedLocation: StateFlow<LocationItem> = _selectedLocation.asStateFlow()

    private val _temperatureUnit = MutableStateFlow(TemperatureUnit.CELSIUS)
    val temperatureUnit: StateFlow<TemperatureUnit> = _temperatureUnit.asStateFlow()

    private val _weatherUiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<LocationItem>>(emptyList())
    val searchResults: StateFlow<List<LocationItem>> = _searchResults.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    private var searchJob: Job? = null

    init {
        // Load initial weather for London
        loadWeather(_selectedLocation.value)
    }

    fun loadWeather(location: LocationItem) {
        _selectedLocation.value = location
        viewModelScope.launch {
            _weatherUiState.value = WeatherUiState.Loading
            try {
                val weatherData = repository.fetchWeather(location)
                _weatherUiState.value = WeatherUiState.Success(weatherData)
            } catch (e: Exception) {
                _weatherUiState.value = WeatherUiState.Error(
                    e.localizedMessage ?: "Failed to retrieve weather update."
                )
            }
        }
    }

    fun refreshWeather() {
        loadWeather(_selectedLocation.value)
    }

    fun toggleUnit() {
        _temperatureUnit.value = if (_temperatureUnit.value == TemperatureUnit.CELSIUS) {
            TemperatureUnit.FAHRENHEIT
        } else {
            TemperatureUnit.CELSIUS
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        if (query.trim().isBlank()) {
            _searchResults.value = emptyList()
            _isSearching.value = false
            return
        }
        _isSearching.value = true
        searchJob = viewModelScope.launch {
            delay(350) // Debounce user typing
            val results = repository.searchCities(query)
            _searchResults.value = results
            _isSearching.value = false
        }
    }

    fun toggleFavorite(location: LocationItem) {
        viewModelScope.launch {
            val isCurrentlyFav = favoriteLocations.value.any { it.id == location.id }
            if (isCurrentlyFav) {
                repository.removeFavorite(location)
            } else {
                repository.saveFavorite(location)
            }
        }
    }

    // Convert helper methods
    fun formatTemp(celsius: Double): String {
        return if (_temperatureUnit.value == TemperatureUnit.CELSIUS) {
            "${celsius.toInt()}°C"
        } else {
            val f = (celsius * 9 / 5) + 32
            "${f.toInt()}°F"
        }
    }

    fun formatTempRaw(celsius: Double): String {
        return if (_temperatureUnit.value == TemperatureUnit.CELSIUS) {
            "${celsius.toInt()}°"
        } else {
            val f = (celsius * 9 / 5) + 32
            "${f.toInt()}°"
        }
    }
}
