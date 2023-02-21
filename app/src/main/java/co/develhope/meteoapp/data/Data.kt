package co.develhope.meteoapp.Data

import java.time.OffsetDateTime

object Data {

    sealed class HomeScreenElements() {
        data class Title(
            val city: String,
            val region: String
        ) : HomeScreenElements()

        data class Next5Days(val next5Days: String) : HomeScreenElements()

        data class HomeCards(
            val day: OffsetDateTime?,
            val dataDay: OffsetDateTime?,
            val min: String,
            val max: String,
            val rain: String,
            val wind: String,
            val icon: Int,
            val degree: String,
            val degree2: String,
            val dataPercent: String,
            val dataKMH: String
        ) : HomeScreenElements()
    }
}