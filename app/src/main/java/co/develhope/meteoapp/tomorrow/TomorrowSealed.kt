package co.develhope.meteoapp.tomorrow

sealed class TomorrowSealed(){
        data class Title (val titleTomorrow : TomorrowTitle) : TomorrowSealed()
        data class Row (val tomorrowRow: TomorrowRow) : TomorrowSealed()
    }