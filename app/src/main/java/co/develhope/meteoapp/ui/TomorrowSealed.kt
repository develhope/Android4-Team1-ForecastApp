package co.develhope.meteoapp.ui

import co.develhope.meteoapp.network.domainmodel.TomorrowRow

sealed class TomorrowSealed(){
        data class Title (val titleTomorrow : TomorrowTitle) : TomorrowSealed()
        data class Row (val tomorrowRow: TomorrowRow) : TomorrowSealed()
    }