package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.dto.DayData
import co.develhope.meteoapp.network.dto.SearchData
import co.develhope.meteoapp.network.dto.WeeklyData

class Repository {

    // Tutte le funzioni che si trovano in questo file devono ritornare domain model


    suspend fun getSearchDetails(userSearch: String): SearchData {
        return RetrofitInstance().serviceGeoCodingApi.getDayEndPointDetails(userSearch)
    }

    suspend fun getDayDetails(): DayData? {

        return if (DataObject.getSelectedCity()?.latitude != null && DataObject.getSelectedCity()?.longitude != null) {
            RetrofitInstance().serviceMeteoApi.getDayEndPointDetails(
                DataObject.getSelectedCity()!!.latitude,
                DataObject.getSelectedCity()!!.longitude
            )
        } else {
            null
        }
    }


    suspend fun getWeeklyDetails(): WeeklyData? {
        return if (DataObject.getSelectedCity()?.latitude != null && DataObject.getSelectedCity()?.longitude != null) {
            RetrofitInstance().serviceMeteoApi.getWeeklyEndPointDetails(
                DataObject.getSelectedCity()!!.latitude,
                DataObject.getSelectedCity()!!.longitude
            ).body()
        } else {
            null
        }
    }
}