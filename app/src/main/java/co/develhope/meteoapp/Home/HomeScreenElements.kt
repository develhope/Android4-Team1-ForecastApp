package co.develhope.meteoapp.Home

sealed class HomeScreenElements() {
    data class TitleHome(val title : Title) : HomeScreenElements()
    data class CardsHome(val cardsHome : HomeCards) : HomeScreenElements()
    data class SubTitleHome(val subTileHome : Next5Days) : HomeScreenElements()
    }