package co.develhope.meteoapp.Data

import org.threeten.bp.OffsetDateTime

data class TodayTitle(
    val city: String,
    val region: String,
    val date: OffsetDateTime
)