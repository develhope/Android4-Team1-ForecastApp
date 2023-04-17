package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenElements
import co.develhope.meteoapp.ui.adapter.home_adapter.Next5Days
import co.develhope.meteoapp.ui.adapter.home_adapter.Title

fun List<HomeCards>?.toHomeCards(name: String, region: String): List<HomeScreenElements> {

    return if (this == null) {
        listOf()
    } else {
        listOf(

            HomeScreenElements.TitleHome(Title("${name}, $region")),
            HomeScreenElements.CardsHome(this.first()),
            HomeScreenElements.SubTitleHome(Next5Days(R.string.next5days)),
        ) +
                this
                    .subList(1, this.lastIndex)
                    .map {
                        HomeScreenElements.CardsHome(it)
                    }
    }
}

