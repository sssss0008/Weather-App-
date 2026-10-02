package com.example.weather.model

import androidx.compose.ui.graphics.Color

enum class WeatherCondition(
    val code: Int,
    val title: String,
    val isNight: Boolean = false,
    val bgGradient: List<Color>,
    val cardBgColor: Color
) {
    CLEAR_SKY(
        code = 0,
        title = "Clear Sky",
        bgGradient = listOf(Color(0xFF29B6F6), Color(0xFF0288D1), Color(0xFF01579B)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    MAINLY_CLEAR(
        code = 1,
        title = "Mainly Clear",
        bgGradient = listOf(Color(0xFF4FC3F7), Color(0xFF0288D1), Color(0xFF0277BD)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    PARTLY_CLOUDY(
        code = 2,
        title = "Partly Cloudy",
        bgGradient = listOf(Color(0xFF78909C), Color(0xFF455A64), Color(0xFF263238)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    OVERCAST(
        code = 3,
        title = "Overcast",
        bgGradient = listOf(Color(0xFF607D8B), Color(0xFF37474F), Color(0xFF263238)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    FOG(
        code = 45,
        title = "Foggy",
        bgGradient = listOf(Color(0xFF90A4AE), Color(0xFF546E7A), Color(0xFF37474F)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    DEPOSITING_RIME_FOG(
        code = 48,
        title = "Freezing Fog",
        bgGradient = listOf(Color(0xFFB0BEC5), Color(0xFF607D8B), Color(0xFF37474F)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    DRIZZLE_LIGHT(
        code = 51,
        title = "Light Drizzle",
        bgGradient = listOf(Color(0xFF4FC3F7), Color(0xFF37474F), Color(0xFF1C313A)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    DRIZZLE_MODERATE(
        code = 53,
        title = "Drizzle",
        bgGradient = listOf(Color(0xFF0288D1), Color(0xFF263238), Color(0xFF102027)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    DRIZZLE_DENSE(
        code = 55,
        title = "Heavy Drizzle",
        bgGradient = listOf(Color(0xFF01579B), Color(0xFF263238), Color(0xFF102027)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    RAIN_SLIGHT(
        code = 61,
        title = "Slight Rain",
        bgGradient = listOf(Color(0xFF0288D1), Color(0xFF37474F), Color(0xFF102027)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    RAIN_MODERATE(
        code = 63,
        title = "Moderate Rain",
        bgGradient = listOf(Color(0xFF01579B), Color(0xFF263238), Color(0xFF000A12)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    RAIN_HEAVY(
        code = 65,
        title = "Heavy Rain",
        bgGradient = listOf(Color(0xFF002171), Color(0xFF0D1B2A), Color(0xFF000000)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    SNOW_SLIGHT(
        code = 71,
        title = "Slight Snow",
        bgGradient = listOf(Color(0xFF81D4FA), Color(0xFF4FC3F7), Color(0xFF0288D1)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    SNOW_MODERATE(
        code = 73,
        title = "Moderate Snow",
        bgGradient = listOf(Color(0xFFB3E5FC), Color(0xFF81D4FA), Color(0xFF0288D1)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    SNOW_HEAVY(
        code = 75,
        title = "Heavy Snowfall",
        bgGradient = listOf(Color(0xFFE0F7FA), Color(0xFF81D4FA), Color(0xFF01579B)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    THUNDERSTORM(
        code = 95,
        title = "Thunderstorm",
        bgGradient = listOf(Color(0xFF311B92), Color(0xFF1A237E), Color(0xFF000000)),
        cardBgColor = Color(0x33FFFFFF)
    ),
    UNKNOWN(
        code = -1,
        title = "Fair",
        bgGradient = listOf(Color(0xFF0288D1), Color(0xFF01579B), Color(0xFF002171)),
        cardBgColor = Color(0x33FFFFFF)
    );

    companion object {
        fun fromCode(code: Int): WeatherCondition {
            return values().firstOrNull { it.code == code } ?: when (code) {
                56, 57 -> DRIZZLE_MODERATE
                66, 67 -> RAIN_MODERATE
                77 -> SNOW_MODERATE
                80, 81, 82 -> RAIN_HEAVY
                85, 86 -> SNOW_HEAVY
                96, 99 -> THUNDERSTORM
                else -> UNKNOWN
            }
        }
    }
}
