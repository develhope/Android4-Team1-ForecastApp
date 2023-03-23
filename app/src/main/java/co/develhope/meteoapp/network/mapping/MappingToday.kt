package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.ui.adapter.TodayTitle
import org.threeten.bp.OffsetDateTime

fun List<TodayCardInfo>?.toTodayCardInfo(): List<TodayScreenData> {
    if(this == null) return listOf()
    val today = OffsetDateTime.now().toLocalDate()

    return listOf(
        TodayScreenData.TodayTitleObject(
            TodayTitle(
                "${DataObject.getSelectedCity()?.name},",
                DataObject.getSelectedCity()?.region.orEmpty(),
                OffsetDateTime.now()
            )
        ),

        *this.filter { it.date.toLocalDate() == today }.map {
            TodayScreenData.ForecastData(it)
        }
            .toTypedArray(),
    )

}


