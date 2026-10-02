package com.example.weather.model

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current: CurrentUnitsAndValues?,
    val hourly: HourlyData?,
    val daily: DailyData?
)

data class CurrentUnitsAndValues(
    val time: String = "",
    val temperature_2m: Double = 0.0,
    val relative_humidity_2m: Int = 0,
    val apparent_temperature: Double = 0.0,
    val is_day: Int = 1,
    val weather_code: Int = 0,
    val surface_pressure: Double = 0.0,
    val wind_speed_10m: Double = 0.0,
    val wind_direction_10m: Int = 0,
    val uv_index: Double = 0.0,
    val cloud_cover: Int = 0
)

data class HourlyData(
    val time: List<String> = emptyList(),
    val temperature_2m: List<Double> = emptyList(),
    val relative_humidity_2m: List<Int> = emptyList(),
    val weather_code: List<Int> = emptyList(),
    val precipitation_probability: List<Int> = emptyList(),
    val uv_index: List<Double> = emptyList()
)

data class DailyData(
    val time: List<String> = emptyList(),
    val weather_code: List<Int> = emptyList(),
    val temperature_2m_max: List<Double> = emptyList(),
    val temperature_2m_min: List<Double> = emptyList(),
    val sunrise: List<String> = emptyList(),
    val sunset: List<String> = emptyList(),
    val uv_index_max: List<Double> = emptyList(),
    val precipitation_sum: List<Double> = emptyList()
)

// UI models for clean consumption in Compose UI
data class CurrentWeatherUiModel(
    val temperatureC: Double,
    val feelsLikeC: Double,
    val weatherCode: Int,
    val condition: WeatherCondition,
    val humidity: Int,
    val windSpeedKmH: Double,
    val windDirectionDeg: Int,
    val surfacePressureHpa: Double,
    val uvIndex: Double,
    val cloudCover: Int,
    val isDay: Boolean,
    val timeString: String
)

data class HourlyForecastUiModel(
    val timeLabel: String,
    val tempC: Double,
    val weatherCode: Int,
    val precipProb: Int
)

data class DailyForecastUiModel(
    val dateLabel: String,
    val dayOfWeek: String,
    val maxTempC: Double,
    val minTempC: Double,
    val weatherCode: Int,
    val condition: WeatherCondition,
    val precipMm: Double,
    val uvMax: Double,
    val sunriseTime: String,
    val sunsetTime: String
)

data class FullWeatherUiState(
    val locationName: String,
    val current: CurrentWeatherUiModel,
    val hourly: List<HourlyForecastUiModel>,
    val daily: List<DailyForecastUiModel>,
    val aiSummary: String = "",
    val outfitTip: String = "",
    val activityRating: Map<String, String> = emptyMap()
)
