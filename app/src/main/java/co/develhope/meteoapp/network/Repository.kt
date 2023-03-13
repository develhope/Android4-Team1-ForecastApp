package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.dto.DayData
import co.develhope.meteoapp.network.dto.SearchData
import co.develhope.meteoapp.network.dto.WeeklyData
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class Repository {

    // Tutte le funzioni che si trovano in questo file devono ritornare domain model


    suspend fun getSearchDetails(userSearch : String): SearchData {
        return RetrofitInstance().serviceGeoCodingApi.getDayEndPointDetails(userSearch)
    }

    suspend fun getDayDetails(): DayData {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate = OffsetDateTime.now().format(formatter)
        val endDate = OffsetDateTime.now().plusDays(5).format(formatter)

        return RetrofitInstance().serviceMeteoApi.getDayEndPointDetails(
            start_date = startDate,
            end_date = endDate
        )
    }


    suspend fun getWeeklyDetails(): WeeklyData {
        return RetrofitInstance().serviceMeteoApi.getWeeklyEndPointDetails()
    }
}