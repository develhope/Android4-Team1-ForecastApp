package co.develhope.meteoapp.network.dto


import co.develhope.meteoapp.network.domainmodel.Place
import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("results")
    val results: List<Result>
){

    fun toDomain() : List<Place>  {
        return results.map { Place(name = it.name, latitude = it.latitude, longitude = it.longitude, region = it.admin1) }


    }
}