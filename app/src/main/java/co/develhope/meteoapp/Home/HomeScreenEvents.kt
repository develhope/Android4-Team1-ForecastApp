package co.develhope.meteoapp.Home

sealed class HomeScreenEvents {
    object Today : HomeScreenEvents()
    object Tomorrow : HomeScreenEvents()
    data class OtherDay(val day: Int? = 3) : HomeScreenEvents()
}
