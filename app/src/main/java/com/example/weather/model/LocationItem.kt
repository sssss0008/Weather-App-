package com.example.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_locations")
data class LocationItem(
    @PrimaryKey val id: String,
    val name: String,
    val country: String = "",
    val admin1: String = "",
    val latitude: Double,
    val longitude: Double,
    val isCurrentLocation: Boolean = false,
    val isFavorite: Boolean = false,
    val timezone: String = "auto"
)
