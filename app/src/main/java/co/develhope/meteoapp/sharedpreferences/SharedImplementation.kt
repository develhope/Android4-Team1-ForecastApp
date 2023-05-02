package co.develhope.meteoapp.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val CITYSHARED = "city"
private const val LASTCITYSHARED = "lastcity"
private const val TEMPERATURE = "temperature"
private const val CODE = "code"

class SharedImplementation(context: Context, private val gson: Gson) :
    MySharedPrefsInterface {

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        "Place",
        Context.MODE_PRIVATE
    )

    override fun setSelectedCity(place: HourlyItem) {
        setSearchCity(place)

        sharedPrefs
            .edit()
            ?.putString(CITYSHARED, gson.toJson(place)) //creiamo una private val di gson che vale per tutta la classe
            ?.commit()
    }


    override fun getSelectedCity(): Place? {
        val getdati =
            sharedPrefs.getString(CITYSHARED, null)//questa è la stringa che ha tutti i dati
        return try {
            gson.fromJson( //dobbiamo avere un punto centrale nella di con gson, deve essere nel costruttore
                getdati,
                HourlyItem::class.java
            ).city //convertila in oggetto hourlyitem. .city serve a farsì che dopo aver lavorato con hourlyitems ci ritorna comunque place(city è la variabile dove salviamo place)
        } catch (e: java.lang.Exception) {
            Log.e("Casini con le shared", e.toString())
            null
        }
    }

    override fun getSearchCity(): List<HourlyItem> {
        val getdati =
            sharedPrefs.getString(LASTCITYSHARED, null)//questa è la lista che contiene i dati
        return try {
            val listOfMyClassObject = object :
                TypeToken<ArrayList<HourlyItem?>?>() {}.type//gson per convertire una lista ha bisogno di questo costrutto con il toke, è così non ti fare domande
            gson.fromJson(getdati, listOfMyClassObject)
        } catch (e: java.lang.Exception) {
            Log.e("Casini con le shared", e.toString())
            listOf()
        }
    }

    override fun setSearchCity(item: HourlyItem) {
        val list = getSearchCity().toMutableList()

        if (item !in list) { //se la città non è nella lista aggiungila alla prima posizione
            list.add(0, item)

            if (list.size > 5) {
                list.removeAt(list.lastIndex) //se la lista è maggiore di 5 elimina l'ultimo elemento(così non sarà mai maggiore di 5)
            }

            sharedPrefs//infine salva
                .edit()
                ?.putString(LASTCITYSHARED, gson.toJson(list))
                ?.apply()
        }
    }

    override fun setTemperature(temperature: String) {
        sharedPrefs
            .edit()
            ?.putString(TEMPERATURE, temperature)
            ?.apply()
    }

    override fun getTemperature(): String {
        return sharedPrefs
            .getString(TEMPERATURE, "")
            .orEmpty()
    }

    override fun setIconSun(code: Int) {
        sharedPrefs
            .edit()
            ?.putInt(CODE, code)
            ?.apply()
    }

    override fun getIconSun(): Int {
        return sharedPrefs
            .getInt(CODE, 0)
    }

}