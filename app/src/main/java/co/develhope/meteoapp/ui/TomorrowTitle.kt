package co.develhope.meteoapp.ui

import org.threeten.bp.OffsetDateTime

data class TomorrowTitle(
    val city : String,
    val region : String,
    val day : OffsetDateTime,
    )