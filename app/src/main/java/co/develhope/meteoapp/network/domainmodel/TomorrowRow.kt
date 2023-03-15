package co.develhope.meteoapp.network.domainmodel

import org.threeten.bp.OffsetDateTime

data class TomorrowRow(
        // Row Data
        var iconTomorrow: Int,
        var time: OffsetDateTime,
        var degrees: String,
        var percentage: String,

        // CardView Data
        var cvDegrees: String,
        var cvNumberUV: String,
        var cvPercentage2: String,
        var cvNNE: String,
        var cvPercentage: String,
        var cvRainCM: String,
    )