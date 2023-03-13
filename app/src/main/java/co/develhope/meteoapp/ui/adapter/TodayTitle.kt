package co.develhope.meteoapp.ui.adapter

import org.threeten.bp.OffsetDateTime

data class TodayTitle(
    val city: String,
    val region: String,
    val date: OffsetDateTime
)