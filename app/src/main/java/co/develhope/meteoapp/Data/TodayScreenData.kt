package co.develhope.meteoapp.Data

import co.develhope.meteoapp.TodayCardInfo

sealed class TodayScreenData() {

    data class TodayTitleObject(
        val title: TodayTitle
    ) : TodayScreenData()

    data class ForecastData(
        val todayCardInfo: TodayCardInfo
    ) : TodayScreenData()
}