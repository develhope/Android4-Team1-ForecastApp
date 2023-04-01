package co.develhope.meteoapp.network.dto

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents

data class WeeklyData(
    val current_weather: CurrentWeather,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int,
) {
    fun toDomain(): List<HomeCards> {
        return daily.time.mapIndexed { index, time ->
            HomeCards(
                day = time,
                min = R.string.min,
                max = R.string.max,
                rain = R.string.precip,
                wind = R.string.vento_home,
                icon = intToEnumToIcon(daily.weathercode.getOrNull(index)),
                degree = "${
                    daily.temperature_2m_min.getOrNull(index)?.toInt()?.toString()
                }${daily_units.temperature_2m_min}",
                degree2 = "${
                    daily.temperature_2m_max.getOrNull(index)?.toInt()?.toString()
                }${daily_units.temperature_2m_max}",
                dataPercent = "${
                    daily.precipitation_sum.getOrNull(index)?.toInt()?.toString()
                }${daily_units.precipitation_sum}",
                dataKMH = "${
                    daily.windspeed_10m_max.getOrNull(index)?.toInt()?.toString()
                }${daily_units.windspeed_10m_max}",
                key = indexToKey(index)

            )
        }
    }

    private fun intToEnumToIcon(code: Int?): Int {
        return when (code) {
            0 -> DataObject.weatherIcon(Weather.SUNNY)
            1, 2, 3 -> DataObject.weatherIcon(Weather.CLOUDY)
            45, 48 -> DataObject.weatherIcon(Weather.FOGGY)
            51, 53, 55 -> DataObject.weatherIcon(Weather.RAINY)
            56, 57 -> DataObject.weatherIcon(Weather.RAINY)
            71, 73, 75 -> DataObject.weatherIcon(Weather.HEAVYRAIN)
            80, 81, 82 -> DataObject.weatherIcon(Weather.HEAVYRAIN)
            95 -> DataObject.weatherIcon(Weather.HEAVYRAIN)
            96, 99 -> DataObject.weatherIcon(Weather.HEAVYRAIN)
            else -> DataObject.weatherIcon(Weather.SUNNY)
        }
    }

    private fun indexToKey(index: Int): HomeScreenEvents {
        return when (index) {
            0 -> HomeScreenEvents.Today
            1 -> HomeScreenEvents.Tomorrow
            else -> HomeScreenEvents.OtherDay(index)
        }
    }
}