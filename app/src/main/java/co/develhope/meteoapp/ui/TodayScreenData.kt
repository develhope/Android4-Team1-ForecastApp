package co.develhope.meteoapp.ui

import co.develhope.meteoapp.network.domainmodel.TodayCardInfo

sealed class TodayScreenData {

    data class TodayTitleObject(
        val title: TodayTitle
    ) : TodayScreenData()

    data class ForecastData(
        val todayCardInfo: TodayCardInfo
    ) : TodayScreenData()
}