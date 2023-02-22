package co.develhope.meteoapp.Data

import co.develhope.meteoapp.R
import org.threeten.bp.OffsetDateTime

object Data {

    sealed class HomeScreenElements() {
        data class Title(
            val city: String,
        ) : HomeScreenElements()

        data class Next5Days(val next5Days: String) : HomeScreenElements()

        data class HomeCards(
            val day: OffsetDateTime?,
            val min: String,
            val max: String,
            val rain: String,
            val wind: String,
            val icon: Int,
            val degree: String,
            val degree2: String,
            val dataPercent: String,
            val dataKMH: String,
            val key : String
        ) : HomeScreenElements()
    }

    fun getIconSun(weather: String): Int {
        return when(weather){
            "sunny" -> R.drawable.sun_icon
            "cloudy" -> R.drawable.sun_behind_cloud
            "rainy" -> R.drawable.sun_behind_rain_cloud
            else -> R.drawable.sun_icon
        }


    }


    fun getListAdapter(): List<HomeScreenElements> {
        return listOf(
            HomeScreenElements.Title("Palermo, Sicilia"),
            HomeScreenElements.HomeCards(
                OffsetDateTime.now(),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("sunny"),
                "20°",
                "31°",
                "0%",
                "12kmh",
                "Today"),
            HomeScreenElements.Next5Days("NEXT 5 DAYS"),
            HomeScreenElements.HomeCards(
                OffsetDateTime.now().plusDays(1),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "18°",
                "29°",
                "0%",
                "20kmh",
                "Tomorrow"),
            HomeScreenElements.HomeCards(
                OffsetDateTime.now().plusDays(2),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("rainy"),
                "21°",
                "30°",
                "10%",
                "10kmh",
                "2day"),
            HomeScreenElements.HomeCards(
                OffsetDateTime.now().plusDays(3),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "22°",
                "31°",
                "0%",
                "5kmh",
                "3day"),
            HomeScreenElements.HomeCards(
                OffsetDateTime.now().plusDays(4),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("sunny"),
                "10°",
                "21°",
                "0%",
                "6kmh",
                "4day"),
            HomeScreenElements.HomeCards(
                OffsetDateTime.now().plusDays(5),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("rainy"),
                "25°",
                "30°",
                "0%",
                "11kmh",
                "5day")

        )
    }
}