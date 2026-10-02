package com.example.weather.data.remote

data class GeocodingResponse(
    val results: List<GeocodingResult>? = null
)

data class GeocodingResult(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double? = null,
    val feature_code: String? = null,
    val country_code: String? = null,
    val admin1: String? = null,
    val admin2: String? = null,
    val country: String? = null,
    val timezone: String? = null,
    val population: Long? = null
)
