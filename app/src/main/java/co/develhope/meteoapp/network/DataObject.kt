package co.develhope.meteoapp.network

import android.content.SharedPreferences
import android.util.Log
import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


private const val CITYSHARED = "city"
private const val LASTCITYSHARED = "lastcity"

object DataObject {
    private var sharedPrefe: SharedPreferences? = null

    fun setSharedPrefe(sharedPrefe: SharedPreferences?) {
        this.sharedPrefe = sharedPrefe
    }

    fun setSelectedCity(place: HourlyItem) {
        setSearchCity(place)

        sharedPrefe
            ?.edit()
            ?.putString(CITYSHARED, Gson().toJson(place))
            ?.commit()
    }

    fun getSelectedCity(): Place? {
        val getdati = sharedPrefe?.getString(CITYSHARED, null)//questa è la stringa che ha tutti i dati
        return try {
            Gson().fromJson(getdati, HourlyItem::class.java).city //convertila in oggetto hourlyitem. .city serve a farsì che dopo aver lavorato con hourlyitems ci ritorna comunque place(city è la variabile dove salviamo place)
        } catch (e: java.lang.Exception) {
            Log.e("Casini con le shared", e.toString())
            null
        }
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

    fun getSearchCity(): List<HourlyItem> {
        val getdati = sharedPrefe?.getString(LASTCITYSHARED, null)//questa è la lista che contiene i dati
        return try {
            val listOfMyClassObject = object : TypeToken<ArrayList<HourlyItem?>?>() {}.type//gson per convertire una lista ha bisogno di questo costrutto con il toke, è così non ti fare domande
            Gson().fromJson(getdati, listOfMyClassObject)
        } catch (e: java.lang.Exception) {
            Log.e("Casini con le shared", e.toString())
            listOf()
        }
    }

    fun setSearchCity(item: HourlyItem) {
        val list = getSearchCity().toMutableList()

        if (item !in list) { //se la città non è nella lista aggiungila alla prima posizione
            list.add(0, item)

            if(list.size > 5) {
                list.removeAt(list.lastIndex) //se la lista è maggiore di 5 elimina l'ultimo elemento(così non sarà mai maggiore di 5)
            }

            sharedPrefe //infine salva
                ?.edit()
                ?.putString(LASTCITYSHARED, Gson().toJson(list))
                ?.apply()
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

