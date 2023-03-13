package co.develhope.meteoapp.network.dto

import co.develhope.meteoapp.network.domainmodel.HomeCards
import org.threeten.bp.OffsetDateTime

data class Daily(
    val precipitation_sum: List<Double>,
    val rain_sum: List<Double>,
    val sunrise: List<OffsetDateTime>,
    val sunset: List<OffsetDateTime>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<OffsetDateTime>,
    val weathercode: List<Int>,
    val windspeed_10m_max: List<Double>
) {
    fun toDomain(): List<HomeCards> {
        return time.mapIndexed { index, time ->
            HomeCards(
                day = time,
                min = 0,
                max = 0,
                rain = 0,
                wind = 0,
                icon = 0,
                degree = temperature_2m_max.getOrNull(index).toString(),
                degree2 = "",
                dataPercent = "",
                dataKMH = "",
                key =
            )
        }
    }

}