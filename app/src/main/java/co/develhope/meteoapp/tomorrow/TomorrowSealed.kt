package co.develhope.meteoapp.tomorrow

import co.develhope.meteoapp.network.domainmodel.TomorrowRow

sealed class TomorrowSealed(){
        data class Title (val titleTomorrow : TomorrowTitle) : TomorrowSealed()
        data class Row (val tomorrowRow: TomorrowRow) : TomorrowSealed()
    }