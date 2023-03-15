package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import co.develhope.meteoapp.ui.adapter.TomorrowSealed
import co.develhope.meteoapp.ui.adapter.TomorrowTitle
import org.threeten.bp.OffsetDateTime

fun List<TomorrowRow>.toTomorrowRow(): List<TomorrowSealed> {

    return listOf(
        TomorrowSealed.Title(TomorrowTitle("Roma,","Lazio",OffsetDateTime.now())),
        *this.map { TomorrowSealed.Row(it) }.toTypedArray(),
    )
}


