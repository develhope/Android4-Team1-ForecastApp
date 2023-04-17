package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.ui.adapter.TodayTitle
import org.threeten.bp.OffsetDateTime

fun List<TodayCardInfo>?.toTodayCardInfo(name : String, region : String): List<TodayScreenData> {
    if (this == null) return listOf()
    val today = OffsetDateTime.now().toLocalDate()
    val currentHour = OffsetDateTime.now().hour

    return listOf(
        TodayScreenData.TodayTitleObject(
            TodayTitle(
                "${name},",
                region,
                OffsetDateTime.now()
            )
        ),

        *this.filter { it.date.toLocalDate() == today && it.date.hour >= currentHour }.map {
            TodayScreenData.ForecastData(it)
        }
            .toTypedArray(),
    )

}


