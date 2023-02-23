package co.develhope.meteoapp.tomorrow

import org.threeten.bp.OffsetDateTime

data class TomorrowTitle(
    val city : String,
    val region : String,
    val day : OffsetDateTime,
    )