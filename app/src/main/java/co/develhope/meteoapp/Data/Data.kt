package co.develhope.meteoapp.Data

import co.develhope.meteoapp.R
import co.develhope.meteoapp.TodayCardInfo
import co.develhope.meteoapp.Weather
import java.time.OffsetDateTime

object Data {

    //TODAY SCREEN DATA

    sealed class TodayScreenData {

        data class TodayTitle(
            val city: String,
            val region: String,
            val date: OffsetDateTime
        ) : TodayScreenData()

        data class ForecastData(
            val todayCardInfo: TodayCardInfo
        ) : TodayScreenData()
    }

    fun weatherIcon(weather: Weather): Int {
        return when (weather) {
            Weather.SUNNY -> R.drawable.sun_icon
            Weather.RAINY -> R.drawable.sun_behind_rain_cloud
            Weather.CLOUDY -> R.drawable.sun_behind_cloud
            Weather.FOGGY -> R.drawable.sun_behind_cloud
            Weather.WINDY -> R.drawable.sun_behind_cloud
            Weather.HEAVYRAIN -> R.drawable.sun_behind_rain_cloud
        }
    }




}