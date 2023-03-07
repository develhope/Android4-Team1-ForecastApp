package co.develhope.meteoapp.network

import retrofit2.http.GET
import retrofit2.http.Query

interface DayEndpoint {
    @GET("v1/forecast")
    suspend fun getDayEndPointDetails(
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
        @Query("hourly") hourly: List<String> = listOf(
            "temperature_2m",
            "rain",
            "showers",
            "snowfall",
            "weathercode",
            "windspeed_10m"
        ),//andrà nell'endpoint
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "Europe/Berlin",//endpoint
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
    ): DayData


    //suspend fun settimana ecc
}