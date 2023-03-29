package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.dto.SearchData
import retrofit2.http.GET
import retrofit2.http.Query

interface

SearchEndPoint {
        @GET("v1/search")
        suspend fun getDayEndPointDetails(
            @Query("name") name: String
        ): SearchData
}