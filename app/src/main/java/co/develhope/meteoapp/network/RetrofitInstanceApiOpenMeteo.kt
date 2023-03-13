package co.develhope.meteoapp.network

import co.develhope.meteoapp.network.dto.DayData
import co.develhope.meteoapp.network.dto.WeeklyData
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceApiOpenMeteo {

    companion object {

        val BASE_URL = "https://api.open-meteo.com/"
        val client = OkHttpClient.Builder()
            .addInterceptor(getLoggingBody())
            .build()

        fun getLoggingBody(): Interceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }


        fun getRetrofitInstanceApiOpenMeteo(): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        }

        fun getDayEndpoint(): DayEndpoint {
            return getRetrofitInstanceApiOpenMeteo().create(DayEndpoint::class.java)
        }

        suspend fun getDayDetails(): DayData {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val startDate = OffsetDateTime.now().format(formatter)
            val endDate = OffsetDateTime.now().plusDays(5).format(formatter)

            return getDayEndpoint().getDayEndPointDetails(
                start_date = startDate,
                end_date = endDate
            )
        }


        suspend fun getWeeklyDetails(): WeeklyData {
            return getDayEndpoint().getWeeklyEndPointDetails()
        }
    }
}

