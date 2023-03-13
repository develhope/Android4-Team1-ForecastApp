package co.develhope.meteoapp.network.dto


import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    @SerializedName("rain")
    val rain: String?,
    @SerializedName("showers")
    val showers: String?,
    @SerializedName("snowfall")
    val snowfall: String?,
    @SerializedName("temperature_2m")
    val temperature2m: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("weathercode")
    val weathercode: String?,
    @SerializedName("windspeed_10m")
    val windspeed10m: String?
)