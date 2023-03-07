package co.develhope.meteoapp.Home

import org.threeten.bp.OffsetDateTime

data class HomeCards(
    val day: OffsetDateTime?,
    val min: String,
    val max: String,
    val rain: String,
    val wind: String,
    val icon: Int,
    val degree: String,
    val degree2: String,
    val dataPercent: String,
    val dataKMH: String,
    val key : HomeScreenEvents
        )