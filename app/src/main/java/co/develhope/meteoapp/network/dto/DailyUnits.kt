package co.develhope.meteoapp.network.dto

data class DailyUnits(
    val precipitation_sum: String,
    val rain_sum: String,
    val sunrise: String,
    val sunset: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weathercode: String,
    val windspeed_10m_max: String
)