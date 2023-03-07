package co.develhope.meteoapp.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WeeklyEndPoint {
    @GET("v1/forecast")
    suspend fun getWeeklyEndPointDetails(
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
        @Query("daily") daily: List<String> = listOf(
            "weathercode",
            "temperature_2m_max",
            "temperature_2m_min",
            "sunrise",
            "sunset",
            "precipitation_sum",
            "rain_sum",
        ),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "Europe/Berlin",
    ): WeeklyData

}