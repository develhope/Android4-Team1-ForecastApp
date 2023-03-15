package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.ui.adapter.TodayTitle
import org.threeten.bp.OffsetDateTime

fun List<TodayCardInfo>.toTodayCardInfo(): List<TodayScreenData> {

    return listOf(
        TodayScreenData.TodayTitleObject(TodayTitle("Roma,","Lazio",OffsetDateTime.now())),
        *this.map { TodayScreenData.ForecastData(it) }.toTypedArray(),
    )
}


