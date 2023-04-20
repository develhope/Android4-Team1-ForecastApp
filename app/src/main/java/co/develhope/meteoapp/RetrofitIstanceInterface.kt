package co.develhope.meteoapp

import co.develhope.meteoapp.network.ForecastApiService
import co.develhope.meteoapp.network.OffsetDateTimeTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface RetrofitIstanceInterface {

    fun provideGeoCodingRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit

    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit


    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor

    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient

    val serviceMeteoApi : ForecastApiService
}