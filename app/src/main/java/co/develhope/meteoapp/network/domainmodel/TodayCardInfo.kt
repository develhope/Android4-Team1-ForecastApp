package co.develhope.meteoapp.network.domainmodel

import org.threeten.bp.OffsetDateTime
data class TodayCardInfo(
    val date: OffsetDateTime,
    var iconToday: Int,
    val temperature: String,
    val precipitation: String,
    val perc_temperature: String,
    val UV_Index: String,
    val humidity: String,
    val wind: String,
    val coverage: String,
    val rain: String
)
