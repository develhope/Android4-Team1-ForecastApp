package co.develhope.meteoapp.network.remote_model

import co.develhope.meteoapp.network.remote_model.CurrentWeatherX
import co.develhope.meteoapp.network.remote_model.Daily
import co.develhope.meteoapp.network.remote_model.DailyUnits

data class WeeklyData(
    val current_weather: CurrentWeatherX,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)