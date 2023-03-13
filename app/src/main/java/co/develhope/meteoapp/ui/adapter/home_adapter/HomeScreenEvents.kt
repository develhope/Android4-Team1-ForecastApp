package co.develhope.meteoapp.ui.adapter.home_adapter

sealed class HomeScreenEvents {
    object Today : HomeScreenEvents()
    object Tomorrow : HomeScreenEvents() //un altro oggetto che manda a un fragment
    data class OtherDay(val day: Int? = 3) : HomeScreenEvents()
}
