package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.dto.SearchData
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceGeocoding {

    val BASE_URL = "https://geocoding-api.open-meteo.com/"
    val client = OkHttpClient.Builder()
        .addInterceptor(getLoggingBody())
        .build()

    fun getLoggingBody(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    fun getRetrofitInstanceGeoCoding(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    fun getSearchEndpoint(): SearchEndPoint {
        return getRetrofitInstanceGeoCoding().create(SearchEndPoint::class.java)
    }

    suspend fun getSearchDetails(): SearchData {
        return getSearchEndpoint().getDayEndPointDetails()
    }

}