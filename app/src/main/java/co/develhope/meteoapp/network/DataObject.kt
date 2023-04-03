package co.develhope.meteoapp.network

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem

object DataObject {

    private var selectedCity: Place? = null

    fun setSelectedCity(place: Place) {
        selectedCity = place
    }

    fun getSelectedCity(): Place? {
        return selectedCity
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

    val getItemSearchList: List<HourlyItem> = listOf(
        HourlyItem("", "", Place("Monza",  45.69651, 9.18180, "Lombardia")),
        HourlyItem("", "", Place("Milano", 45.46427, 9.18951, "Lombardia")),
        HourlyItem("", "", Place("Agrigento", 37.31065, 13.57661, "Sicilia"))
    )

    fun getSearchCity(): List<HourlyItem> {

        return getItemSearchList
    }

    fun intToEnumToIcon(code: Int?): Int {
        return when (code) {
            0 -> weatherIcon(Weather.SUNNY)
            1, 2, 3 -> weatherIcon(Weather.CLOUDY)
            45, 48 -> weatherIcon(Weather.FOGGY)
            51, 53, 55 -> weatherIcon(Weather.RAINY)
            56, 57 -> weatherIcon(Weather.RAINY)
            71, 73, 75 -> weatherIcon(Weather.HEAVYRAIN)
            80, 81, 82 -> weatherIcon(Weather.HEAVYRAIN)
            95 -> weatherIcon(Weather.HEAVYRAIN)
            96, 99 -> weatherIcon(Weather.HEAVYRAIN)
            else -> weatherIcon(Weather.SUNNY)
        }

    }

}

