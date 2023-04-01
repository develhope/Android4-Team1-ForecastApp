package co.develhope.meteoapp.ui.SearchScreen

import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.network.domainmodel.Weather


data class HourlyItem(
    val degrees: String,
    val weather: String,
    val city: Place?
)



