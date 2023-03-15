package co.develhope.meteoapp.network.dto

import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("rain")
    val rain: List<Double>,
    @SerializedName("showers")
    val showers: List<Double>,
    @SerializedName("snowfall")
    val snowfall: List<Double?>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<org.threeten.bp.OffsetDateTime>,
    @SerializedName("weathercode")
    val weathercode: List<Int>,
    @SerializedName("windspeed_10m")
    val windspeed10m: List<Double>,
    @SerializedName("relativehumidity_2m")
    val relativeHumidity: List<Int>,
    @SerializedName("uv_index_max")
    val uvindexmax: List<Int>,
    @SerializedName("cloudcover")
    val cloudcover: List<Int>,

    )



