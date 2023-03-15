package co.develhope.meteoapp.network.dto

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import co.develhope.meteoapp.network.domainmodel.Weather
import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime

data class DayData(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val hourly: Hourly,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
){
    fun toDomain(): List<TomorrowRow> {
        return hourly.time.mapIndexed { index, time ->
            TomorrowRow(
                time = time,
                iconTomorrow = if (time.hour in 0..5) {
                    R.drawable.crescent_moon
                } else {
                    DataObject.intToEnumToIcon(hourly.weathercode.getOrNull(index))
                },
                degrees = "${hourly.temperature2m.getOrNull(index)?.toInt().toString()}${hourlyUnits.temperature2m}",
                percentage = "${hourly.relativeHumidity.getOrNull(index)?.toInt().toString()}${hourlyUnits.relativehumidity2m}",
                cvDegrees = "${hourly.temperature2m.getOrNull(index)?.toInt().toString()}${hourlyUnits.temperature2m}",
                cvNumberUV = "0",
                cvPercentage2 = "${hourly.relativeHumidity.getOrNull(index)?.toInt().toString()}${hourlyUnits.relativehumidity2m}",
                cvNNE = "${hourly.windspeed10m.getOrNull(index)?.toInt().toString()}${hourlyUnits.windspeed10m}",
                cvPercentage = "${hourly.cloudcover.getOrNull(index)?.toInt().toString()}%",
                cvRainCM = "${hourly.rain.getOrNull(index)?.toInt().toString()}${hourlyUnits.rain}"

            )
        }

    }

    fun toDomainToday(): List<TodayCardInfo> {
        return hourly.time.mapIndexed { index, time ->
            TodayCardInfo(
                date = time,
                iconToday = if (time.hour in 0..5) {
                    R.drawable.crescent_moon
                } else {
                    DataObject.intToEnumToIcon(hourly.weathercode.getOrNull(index))
                },
                temperature = "${hourly.temperature2m.getOrNull(index)?.toInt().toString()}${hourlyUnits.temperature2m}",
                precipitation = "${hourly.relativeHumidity.getOrNull(index)?.toInt().toString()}${hourlyUnits.relativehumidity2m}",
                perc_temperature = "${hourly.temperature2m.getOrNull(index)?.toInt().toString()}${hourlyUnits.temperature2m}",
                UV_Index = "0",
                humidity = "${hourly.relativeHumidity.getOrNull(index)?.toInt().toString()}${hourlyUnits.relativehumidity2m}",
                wind = "${hourly.windspeed10m.getOrNull(index)?.toInt().toString()}${hourlyUnits.windspeed10m}",
                coverage = "${hourly.cloudcover.getOrNull(index)?.toInt().toString()}%",
                rain = "${hourly.rain.getOrNull(index)?.toInt().toString()}${hourlyUnits.rain}"
            )
        }

    }


}





