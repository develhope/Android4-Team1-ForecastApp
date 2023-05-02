package co.develhope.meteoapp.sharedpreferences

import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem

interface MySharedPrefsInterface {

    fun setSelectedCity(place: HourlyItem)

    fun getSelectedCity(): Place?

    fun getSearchCity(): List<HourlyItem>

    fun setSearchCity(item: HourlyItem)

    fun setTemperature(temperature: String)
    fun getTemperature(): String
    fun setIconSun(code: Int)
    fun getIconSun(): Int

}