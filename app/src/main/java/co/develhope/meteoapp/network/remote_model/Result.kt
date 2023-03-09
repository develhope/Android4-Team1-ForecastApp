package co.develhope.meteoapp.network.remote_model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("admin1")
    val admin1: String?,
    @SerializedName("admin1_id")
    val admin1Id: Int?,
    @SerializedName("admin2")
    val admin2: String?,
    @SerializedName("admin2_id")
    val admin2Id: Int?,
    @SerializedName("admin3")
    val admin3: String?,
    @SerializedName("admin3_id")
    val admin3Id: Int?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("country_id")
    val countryId: Int?,
    @SerializedName("elevation")
    val elevation: Double?,
    @SerializedName("feature_code")
    val featureCode: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("population")
    val population: Int?,
    @SerializedName("postcodes")
    val postcodes: List<String?>?,
    @SerializedName("timezone")
    val timezone: String?
)