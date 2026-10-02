package com.example.weather.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.LocationItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM favorite_locations")
    fun getAllFavoriteLocations(): Flow<List<LocationItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteLocation(location: LocationItem)

    @Delete
    suspend fun deleteFavoriteLocation(location: LocationItem)

    @Query("DELETE FROM favorite_locations WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)

    @Query("SELECT * FROM weather_cache WHERE locationId = :locationId LIMIT 1")
    suspend fun getCachedWeather(locationId: String): WeatherCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheWeather(cache: WeatherCacheEntity)
}
