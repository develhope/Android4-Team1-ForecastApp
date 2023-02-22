package co.develhope.meteoapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.TodayCardForecastItemBinding
import co.develhope.meteoapp.databinding.TodayScreenTitleItemBinding
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class TodayScreenAdapter(
    private var items: List<Data.TodayScreenData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val forecastTodayScreen = 1
    private val titleTodayScreen = 0


    class TodayForecastCardViewHolder(private val rowCardForecastItemBinding: TodayCardForecastItemBinding) :
        RecyclerView.ViewHolder(rowCardForecastItemBinding.root) {
        fun bind(card: Data.TodayScreenData.ForecastData) {
            rowCardForecastItemBinding.tvTodayHour.text = card.todayCardInfo.date.hour.toString()
            rowCardForecastItemBinding.ivTodayIcon.setImageResource(Data.weatherIcon(card.todayCardInfo.weather))
            "${card.todayCardInfo.temperature}°".also {
                rowCardForecastItemBinding.tvTodayTemperature.text = it
            }
            "${card.todayCardInfo.precipitation}%".also {
                rowCardForecastItemBinding.tvTodayHumidity.text = it
            }
            "${card.todayCardInfo.perc_temperature}°".also {
                rowCardForecastItemBinding.tvPerceivedTemperature.text = it
            }

            rowCardForecastItemBinding.tvIndexValue.text = card.todayCardInfo.UV_Index.toString()

            "${card.todayCardInfo.humidity}%".also {
                rowCardForecastItemBinding.tvHumidityValue.text = it
            }
            "SSE ${card.todayCardInfo.wind}km/h".also {
                rowCardForecastItemBinding.tvWindValue.text = it
            }
            "${card.todayCardInfo.coverage}%".also {
                rowCardForecastItemBinding.tvCoverageValue.text = it
            }
            "${card.todayCardInfo.rain}cm".also { rowCardForecastItemBinding.tvRainValue.text = it }


        }
    }

    class TodayTitleViewHolder(private val todayScreenTitleItemBinding: TodayScreenTitleItemBinding) :
        RecyclerView.ViewHolder(todayScreenTitleItemBinding.root) {
        fun bind(title: Data.TodayScreenData.TodayTitle) {
            "${title.city}, ${title.region}".also {
                todayScreenTitleItemBinding.tvTodayLocation.text = it
            }

            val todayDateTime = title.date
            val todayOffSetDateTime =
                OffsetDateTime.ofInstant(todayDateTime.toInstant(), ZoneOffset.UTC)
            val todayFormattedDate =
                DateTimeFormatter.ofPattern("dd MMMM YYYY").format(todayOffSetDateTime)
            todayScreenTitleItemBinding.tvTodayDate.text = todayFormattedDate
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            forecastTodayScreen ->
                TodayForecastCardViewHolder(
                    TodayCardForecastItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )

            titleTodayScreen ->
                TodayTitleViewHolder(
                    TodayScreenTitleItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            else -> throw java.lang.IllegalArgumentException("ERROR")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is TodayForecastCardViewHolder -> holder.bind(items[position] as Data.TodayScreenData.ForecastData)
            is TodayTitleViewHolder -> holder.bind(items[position] as Data.TodayScreenData.TodayTitle)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {

            is Data.TodayScreenData.TodayTitle -> titleTodayScreen
            is Data.TodayScreenData.ForecastData -> forecastTodayScreen

        }

    }

}