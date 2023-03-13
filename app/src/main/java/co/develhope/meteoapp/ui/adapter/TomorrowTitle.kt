package co.develhope.meteoapp.ui.adapter

import org.threeten.bp.OffsetDateTime

data class TomorrowTitle(
    val city : String,
    val region : String,
    val day : OffsetDateTime,
    )