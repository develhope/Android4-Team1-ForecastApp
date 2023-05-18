package co.develhope.meteoapp.network.dto

import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
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
)