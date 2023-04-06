package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.dto.DayData
import co.develhope.meteoapp.network.dto.WeeklyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("v1/forecast")
    suspend fun getDayEndPointDetails(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: List<String> = listOf(
            "temperature_2m",
            "rain",
            "showers",
            "snowfall",
            "weathercode",
            "windspeed_10m",
            "relativehumidity_2m",
            "cloudcover"
        ),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "Europe/Berlin",
    ): DayData

    @GET("v1/forecast")
    suspend fun getWeeklyEndPointDetails(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: List<String> = listOf(
            "weathercode",
            "temperature_2m_max",
            "temperature_2m_min",
            "sunrise",
            "sunset",
            "precipitation_sum",
            "rain_sum",
            "windspeed_10m_max"
        ),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "Europe/Berlin",
    ): Response<WeeklyData>
}