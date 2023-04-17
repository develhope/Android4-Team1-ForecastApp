package co.develhope.meteoapp.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val CITYSHARED = "city"
private const val LASTCITYSHARED = "lastcity"

class SharedImplementation(context: Context) :
    MySharedPrefsInterface {

    private  var sharedPrefs : SharedPreferences

    init {
        sharedPrefs = context.getSharedPreferences("Place", AppCompatActivity.MODE_PRIVATE)
    }

    override fun setSelectedCity(place: HourlyItem) {
        setSearchCity(place)

        sharedPrefs
            .edit()
            ?.putString(CITYSHARED, Gson().toJson(place))
            ?.commit()
    }

    override fun getSelectedCity(): Place? {
        val getdati =
            sharedPrefs.getString(CITYSHARED, null)//questa è la stringa che ha tutti i dati
        return try {
            Gson().fromJson(
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
            Gson().fromJson(getdati, listOfMyClassObject)
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
                ?.putString(LASTCITYSHARED, Gson().toJson(list))
                ?.apply()
        }
    }

}