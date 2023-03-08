package co.develhope.meteoapp.network.remote_model

import co.develhope.meteoapp.network.remote_model.CurrentWeatherXX
import co.develhope.meteoapp.network.remote_model.DailyUnitsX
import co.develhope.meteoapp.network.remote_model.DailyX

data class WeeklyDataX(
    val current_weather: CurrentWeatherXX,
    val daily: DailyX,
    val daily_units: DailyUnitsX,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)