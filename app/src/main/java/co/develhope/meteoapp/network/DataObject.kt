package co.develhope.meteoapp.network

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Weather

object DataObject {

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

