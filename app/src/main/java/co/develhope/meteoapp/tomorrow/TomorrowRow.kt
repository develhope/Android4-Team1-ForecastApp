package co.develhope.meteoapp.tomorrow

import org.threeten.bp.OffsetDateTime

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
        var visibility: Boolean = false
    )