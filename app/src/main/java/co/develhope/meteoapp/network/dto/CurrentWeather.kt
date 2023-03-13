package co.develhope.meteoapp.network.dto

import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime

data class CurrentWeather(
    @SerializedName("temperature")
    val temperature: Double?,
    @SerializedName("time")
    val time: OffsetDateTime?,
    @SerializedName("weathercode")
    val weathercode: Int?,
    @SerializedName("winddirection")
    val winddirection: Double?,
    @SerializedName("windspeed")
    val windspeed: Double?
)