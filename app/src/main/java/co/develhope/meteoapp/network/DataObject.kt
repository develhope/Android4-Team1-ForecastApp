package co.develhope.meteoapp.network

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem

object DataObject {

    lateinit var cityName: String
    lateinit var cityCountry: String
    var cityLatitude: Double = 41.8955
    var cityLongitude: Double = 12.4823

    private var selectedCity: Place? = Place(
        name = "Napoli",
        region = "Campania",
        latitude = 40.8531,
        longitude = 14.3055
    )

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
        HourlyItem(12, Weather.SUNNY, Place("Palermo", 38.13205, 13.33561, "Sicilia")),
        HourlyItem(12, Weather.CLOUDY, Place("Catanzaro", 38.88247, 16.60086, "Calabria")),
        HourlyItem(12, Weather.RAINY, Place("Roma", 41.89193, 12.51133, "Lazio"))
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

