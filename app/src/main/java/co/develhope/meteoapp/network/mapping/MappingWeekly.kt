package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.Data.DataObject
import co.develhope.meteoapp.Home.*
import co.develhope.meteoapp.Weather
import co.develhope.meteoapp.network.remote_model.WeeklyData
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset

fun WeeklyData.toHomeCards(): List<HomeScreenElements> {

    return listOf(
        HomeScreenElements.TitleHome(Title("Palermo, Sicilia")),
        HomeScreenElements.CardsHome(
            HomeCards(
                LocalDate.parse(this.daily.time.getOrNull(0)?.toString() ?: "")
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime(),
                "min",
                "max",
                "precip.",
                "vento",
                DataObject.weatherIcon(Weather.SUNNY),
                "${
                    this.daily.temperature_2m_min.getOrNull(0)?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(0)?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(0)?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "12kmh",//documentazione
                key = HomeScreenEvents.Today
            )
        ),
        HomeScreenElements.SubTitleHome(Next5Days("PROSSIMI 5 GIORNI")),
        HomeScreenElements.CardsHome(
            HomeCards(
                LocalDate.parse(this.daily.time.getOrNull(1)?.toString() ?: "")
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime(),
                "min",
                "max",
                "precip.",
                "vento",
                DataObject.weatherIcon(Weather.SUNNY),
                "${
                    this.daily.temperature_2m_min.getOrNull(1)?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(1)?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(1)?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "20kmh",
                key = HomeScreenEvents.Tomorrow
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                LocalDate.parse(this.daily.time.getOrNull(2)?.toString() ?: "")
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime(),
                "min",
                "max",
                "precip.",
                "vento",
                DataObject.weatherIcon(Weather.SUNNY),
                "${
                    this.daily.temperature_2m_min.getOrNull(2)?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(2)?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(2)?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "10kmh",
                key = HomeScreenEvents.OtherDay(3)
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                LocalDate.parse(this.daily.time.getOrNull(3)?.toString() ?: "")
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime(),
                "min",
                "max",
                "precip.",
                "vento",
                DataObject.weatherIcon(Weather.SUNNY),
                "${
                    this.daily.temperature_2m_min.getOrNull(3)?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(3)?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(3)?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "5kmh",
                key = HomeScreenEvents.OtherDay(4)
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                LocalDate.parse(this.daily.time.getOrNull(4)?.toString() ?: "")
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime(),
                "min",
                "max",
                "precip.",
                "vento",
                DataObject.weatherIcon(Weather.SUNNY),
                "${
                    this.daily.temperature_2m_min.getOrNull(4)?.toString() ?: ""
                } ${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(4)?.toString() ?: ""
                } ${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(4)?.toString() ?: ""
                } ${this.daily_units.rain_sum}",
                "6kmh",
                key = HomeScreenEvents.OtherDay(5)
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                LocalDate.parse(this.daily.time.getOrNull(5)?.toString() ?: "")
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime(),
                "min",
                "max",
                "precip.",
                "vento",
                DataObject.weatherIcon(Weather.SUNNY),
                "${
                    this.daily.temperature_2m_min.getOrNull(5)?.toString() ?: ""
                } ${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(5)?.toString() ?: ""
                } ${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(5)?.toString() ?: ""
                } ${this.daily_units.rain_sum}",//o rain_sum
                "11kmh",
                key = HomeScreenEvents.OtherDay(6)
            )
        )
    )

}