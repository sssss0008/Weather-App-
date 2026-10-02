package com.example.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_cache")
data class WeatherCacheEntity(
    @PrimaryKey val locationId: String,
    val jsonContent: String,
    val timestamp: Long = System.currentTimeMillis()
)
