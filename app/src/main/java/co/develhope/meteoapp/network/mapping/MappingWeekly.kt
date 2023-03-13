package co.develhope.meteoapp.network.mapping

import co.develhope.meteoapp.network.DataObject.weatherIcon
import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.network.dto.WeeklyData
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenElements
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
import co.develhope.meteoapp.ui.adapter.home_adapter.Next5Days
import co.develhope.meteoapp.ui.adapter.home_adapter.Title
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset

fun WeeklyData.toHomeCards(): List<HomeScreenElements> {

    return listOf(
        HomeScreenElements.TitleHome(Title("Palermo, Sicilia")),
        HomeScreenElements.CardsHome(
            HomeCards(
                this.daily.time.getOrNull(0),
                R.string.min,
                R.string.max,
                R.string.precip,
                R.string.vento_home,
                intToEnumToIcon(this.daily.weathercode[0]),
                "${
                    this.daily.temperature_2m_min.getOrNull(0)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(0)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(0)?.toInt()?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "${
                    this.daily.windspeed_10m_max.getOrNull(0)?.toInt()?.toString() ?: ""
                }${this.daily_units.windspeed_10m_max}",
                key = HomeScreenEvents.Today
            )
        ),
        HomeScreenElements.SubTitleHome(Next5Days("PROSSIMI 5 GIORNI")),
        HomeScreenElements.CardsHome(
            HomeCards(
                this.daily.time.getOrNull(1),
                R.string.min,
                R.string.max,
                R.string.precip,
                R.string.vento_home,
                intToEnumToIcon(this.daily.weathercode[1]),
                "${
                    this.daily.temperature_2m_min.getOrNull(1)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(1)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(1)?.toInt()?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "${
                    this.daily.windspeed_10m_max.getOrNull(1)?.toInt()?.toString() ?: ""
                }${this.daily_units.windspeed_10m_max}",
                key = HomeScreenEvents.Tomorrow
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                this.daily.time.getOrNull(2),

                R.string.min,
                R.string.max,
                R.string.precip,
                R.string.vento_home,
                intToEnumToIcon(this.daily.weathercode[2]),
                "${
                    this.daily.temperature_2m_min.getOrNull(2)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(2)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(2)?.toInt()?.toString() ?: ""
                }${this.daily_units.rain_sum}",
                "${
                    this.daily.windspeed_10m_max.getOrNull(2)?.toInt()?.toString() ?: ""
                }${this.daily_units.windspeed_10m_max}",
                key = HomeScreenEvents.OtherDay(3)
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                this.daily.time.getOrNull(3),

                R.string.min,
                R.string.max,
                R.string.precip,
                R.string.vento_home,
                intToEnumToIcon(this.daily.weathercode[3]),
                "${
                    this.daily.temperature_2m_min.getOrNull(3)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(3)?.toInt()?.toString() ?: ""
                }${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(3)?.toInt()?.toString() ?: ""
                } ${this.daily_units.rain_sum}",
                "${
                    this.daily.windspeed_10m_max.getOrNull(3)?.toInt()?.toString() ?: ""
                }${this.daily_units.windspeed_10m_max}",
                key = HomeScreenEvents.OtherDay(4)
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                this.daily.time.getOrNull(4),

                R.string.min,
                R.string.max,
                R.string.precip,
                R.string.vento_home,
                intToEnumToIcon(this.daily.weathercode[4]),
                "${
                    this.daily.temperature_2m_min.getOrNull(4)?.toInt()?.toString() ?: ""
                } ${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(4)?.toInt()?.toString() ?: ""
                } ${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(4)?.toInt()?.toString() ?: ""
                } ${this.daily_units.rain_sum}",
                "${
                    this.daily.windspeed_10m_max.getOrNull(4)?.toInt()?.toString() ?: ""
                }${this.daily_units.windspeed_10m_max}",
                key = HomeScreenEvents.OtherDay(5)
            )
        ),
        HomeScreenElements.CardsHome(
            HomeCards(
                this.daily.time.getOrNull(5),

                R.string.min,
                R.string.max,
                R.string.precip,
                R.string.vento_home,
                intToEnumToIcon(this.daily.weathercode[5]),
                "${
                    this.daily.temperature_2m_min.getOrNull(5)?.toInt()?.toString() ?: ""
                } ${this.daily_units.temperature_2m_min}",
                "${
                    this.daily.temperature_2m_max.getOrNull(5)?.toInt()?.toString() ?: ""
                } ${this.daily_units.temperature_2m_max}",
                "${
                    this.daily.precipitation_sum.getOrNull(5)?.toInt()?.toString() ?: ""
                } ${this.daily_units.rain_sum}",//o rain_sum
                "${
                    this.daily.windspeed_10m_max.getOrNull(5)?.toInt()?.toString() ?: ""
                }${this.daily_units.windspeed_10m_max}",
                key = HomeScreenEvents.OtherDay(6)
            )
        )
    )

}
fun intToEnumToIcon(code: Int): Int {
    return when (code) {
        0 ->  weatherIcon(Weather.SUNNY)
        1,2,3 -> weatherIcon(Weather.CLOUDY)
        45,48 -> weatherIcon(Weather.FOGGY)
        51,53,55 -> weatherIcon(Weather.RAINY)
        56,57 -> weatherIcon(Weather.RAINY)
        71,73,75 ->weatherIcon(Weather.HEAVYRAIN)
        80,81,82 -> weatherIcon(Weather.HEAVYRAIN)
        95 -> weatherIcon(Weather.HEAVYRAIN)
        96,99 ->weatherIcon(Weather.HEAVYRAIN)
        else -> weatherIcon(Weather.SUNNY)
    }
}