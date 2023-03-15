package co.develhope.meteoapp.network.domainmodel

import org.threeten.bp.OffsetDateTime

data class TomorrowRow(
        // Row Data
        var time: OffsetDateTime,
        var degrees: String,
        var percentage: String,

        // CardView Data
        var cvDegrees: String,
        var cvNumberUV: Int,
        var cvPercentage2: Int,
        var cvNNE: String,
        var cvPercentage: Int,
        var cvRainCM: String,
    )