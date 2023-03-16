package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import co.develhope.meteoapp.ui.adapter.TomorrowSealed
import co.develhope.meteoapp.ui.adapter.TomorrowTitle
import org.threeten.bp.OffsetDateTime

fun List<TomorrowRow>.toTomorrowRow(): List<TomorrowSealed> {
    val tomorrow = OffsetDateTime.now().plusDays(1).toLocalDate()

    return listOf(
        TomorrowSealed.Title(TomorrowTitle("Roma,","Lazio",OffsetDateTime.now())),

        *this.filter { it.time.toLocalDate() == tomorrow }.map { TomorrowSealed.Row(it) }.toTypedArray(),
    )
}


