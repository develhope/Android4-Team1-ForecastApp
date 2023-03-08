package co.develhope.meteoapp.network

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndPoint {
        @GET("v1/search?name=palermo")
        suspend fun getDayEndPointDetails(
            @Query("name") name: String = "Palermo",
        ): SearchData
}