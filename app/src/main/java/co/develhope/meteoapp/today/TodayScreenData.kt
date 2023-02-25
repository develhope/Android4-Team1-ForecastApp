package co.develhope.meteoapp.today

sealed class TodayScreenData() {

    data class TodayTitleObject(
        val title: TodayTitle
    ) : TodayScreenData()

    data class ForecastData(
        val todayCardInfo: TodayCardInfo
    ) : TodayScreenData()
}