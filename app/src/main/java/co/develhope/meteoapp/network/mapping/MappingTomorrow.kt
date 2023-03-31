package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import co.develhope.meteoapp.ui.adapter.TomorrowSealed
import co.develhope.meteoapp.ui.adapter.TomorrowTitle
import org.threeten.bp.OffsetDateTime

fun List<TomorrowRow>?.toTomorrowRow(day: Int): List<TomorrowSealed> {
    if (this == null) return listOf()
    val tomorrow = OffsetDateTime.now().plusDays(day.toLong()).toLocalDate()

    return listOf(
        TomorrowSealed.Title(
            TomorrowTitle(
                "${DataObject.getSelectedCity()?.name}, ",
                DataObject.getSelectedCity()?.region.orEmpty(),
                OffsetDateTime.now()
            )
        ),

        *this
            .filter {
                it.time.toLocalDate() == tomorrow
            }
            .map {
                TomorrowSealed.Row(it)
            }
            .toTypedArray(),
    )
}


