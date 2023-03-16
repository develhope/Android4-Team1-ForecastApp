package co.develhope.meteoapp.network

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem

object DataObject {

    lateinit var  cityName : String

    private var selectedCity : Place? = Place(
        name = "Napoli",
        region = "Campania",
        latitude = 40.8531,
        longitude = 14.3055
    )
    fun setSelectedCity(place: Place){
        selectedCity = place
    }
    fun getSelectedCity(): Place?{
        return  selectedCity
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

    val getItemSearchList : List<HourlyItem> = listOf(
        HourlyItem(12, Weather.SUNNY, Place("Palermo", 13.33561, 38.13205,"Sicilia" )),
        HourlyItem(12, Weather.CLOUDY, Place("Catanzaro", 16.60008600,38.8824700,"Calabria" )),
        HourlyItem(12, Weather.RAINY, Place("Roma", 41.891930012,12.5113300,"Lazio", )))

    fun getSearchCity() : List<HourlyItem>{

        return getItemSearchList
    }

}

