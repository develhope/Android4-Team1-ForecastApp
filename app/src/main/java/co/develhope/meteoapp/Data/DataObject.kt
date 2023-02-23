package co.develhope.meteoapp.Data

import co.develhope.meteoapp.R
import co.develhope.meteoapp.TodayCardInfo
import co.develhope.meteoapp.Weather
import java.time.OffsetDateTime

object DataObject {
    data class TomorrowTitle(
        val city: String,
        val region: String,
        val day: OffsetDateTime,
    )

    data class TomorrowRow(
        // Row Data
        var time: OffsetDateTime,
        var degrees: Int,
        var percentage: Int,

        // CardView Data
        var cvDegrees: Int,
        var cvNumberUV: Int,
        var cvPercentage2: Int,
        var cvNNE: String,
        var cvPercentage: Int,
        var cvRainCM: Int,
    )


    sealed class TomorrowSealed() {
        data class Title(val titleTomorrow: TomorrowTitle) : TomorrowSealed()
        data class Row(val tomorrowRow: TomorrowRow) : TomorrowSealed()
    }

    //TODAY SCREEN DATA

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

