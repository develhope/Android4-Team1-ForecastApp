package co.develhope.meteoapp.ui.adapter.home_adapter

sealed class HomeScreenEvents {
    object Today : HomeScreenEvents()
    object Tomorrow : HomeScreenEvents()
    data class OtherDay(val day: Int?) : HomeScreenEvents()
}
