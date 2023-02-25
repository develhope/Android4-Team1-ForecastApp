package co.develhope.meteoapp.today

import org.threeten.bp.OffsetDateTime

data class TodayTitle(
    val city: String,
    val region: String,
    val date: OffsetDateTime
)