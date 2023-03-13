package co.develhope.meteoapp.network.domainmodel

import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
import org.threeten.bp.OffsetDateTime

data class HomeCards(
    val day: OffsetDateTime?,
    val min: Int,
    val max: Int,
    val rain: Int,
    val wind: Int,
    val icon: Int,
    val degree: String,
    val degree2: String,
    val dataPercent: String,
    val dataKMH: String,
    val key: HomeScreenEvents
)