package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.remote_model.SearchData
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndPoint {
        @GET("v1/search")
        suspend fun getDayEndPointDetails(
            @Query("name") name: String = "Palermo",
        ): SearchData
}