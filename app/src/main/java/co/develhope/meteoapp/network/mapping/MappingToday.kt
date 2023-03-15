package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.ui.adapter.TodayTitle
import org.threeten.bp.OffsetDateTime

fun List<TodayCardInfo>.toTodayCardInfo(): List<TodayScreenData> {

    val today = OffsetDateTime.now().toLocalDate()

    return listOf(
        TodayScreenData.TodayTitleObject(TodayTitle("Roma,", "Lazio", OffsetDateTime.now())),

        *this.filter { it.date.toLocalDate() == today }.map { TodayScreenData.ForecastData(it) }
            .toTypedArray(),
    )

}


