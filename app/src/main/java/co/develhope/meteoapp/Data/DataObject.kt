package co.develhope.meteoapp.Data

import java.time.OffsetDateTime

object DataObject {
    data class TomorrowTitle(
        val city : String,
        val region : String,
        val day : OffsetDateTime,
    )

    data class TomorrowRow(
        // Row Data
        var time: OffsetDateTime,
        var degrees: Int,
        var percentage: Int,

        // CardView Data
        var cvDegrees: Int,
        var cvNumberUV: Int,
        var cvPercentage2: Int,
        var cvNNE: String,
        var cvPercentage: Int,
        var cvRainCM: Int,
    )


    sealed class TomorrowSealed(){
        data class Title (val titleTomorrow : TomorrowTitle) : TomorrowSealed()
        data class Row (val tomorrowRow: TomorrowRow) : TomorrowSealed()
    }


}

