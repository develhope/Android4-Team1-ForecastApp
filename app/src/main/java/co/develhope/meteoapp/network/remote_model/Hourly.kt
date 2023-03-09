package co.develhope.meteoapp.network.remote_model

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("rain")
    val rain: List<Double?>?,
    @SerializedName("showers")
    val showers: List<Double?>?,
    @SerializedName("snowfall")
    val snowfall: List<Double?>?,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double?>?,
    @SerializedName("time")
    val time: List<String?>?,
    @SerializedName("weathercode")
    val weathercode: List<Int?>?,
    @SerializedName("windspeed_10m")
    val windspeed10m: List<Double?>?
)

