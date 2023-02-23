package co.develhope.meteoapp.Data

import co.develhope.meteoapp.R
import co.develhope.meteoapp.Weather

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

}

