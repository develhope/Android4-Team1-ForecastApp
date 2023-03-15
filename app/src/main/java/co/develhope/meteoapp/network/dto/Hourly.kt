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

    ) {
    fun toDomain(): List<TomorrowRow> {
        return time.mapIndexed { index, hourly ->
            TomorrowRow(
                time = hourly,
                degrees = "${temperature2m.getOrNull(index)?.toInt().toString()}°",
                percentage = "${relativeHumidity.getOrNull(index)?.toInt().toString()}%",
                cvDegrees = "${temperature2m.getOrNull(index)?.toInt().toString()}°",
                cvNumberUV = 0,
                cvPercentage2 = 0,
                cvNNE = windspeed10m.getOrNull(index)?.toInt().toString(),
                cvPercentage = 0,
                cvRainCM = rain.getOrNull(index)?.toInt().toString()
            )
        }

    }
}



