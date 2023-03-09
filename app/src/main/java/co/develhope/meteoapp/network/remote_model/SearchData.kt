package co.develhope.meteoapp.network.remote_model


import co.develhope.meteoapp.network.remote_model.Result
import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double?,
    @SerializedName("results")
    val results: List<Result?>?
)