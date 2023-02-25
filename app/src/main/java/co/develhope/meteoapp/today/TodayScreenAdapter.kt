package co.develhope.meteoapp.today


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.Data.DataObject
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.TodayCardForecastItemBinding
import co.develhope.meteoapp.databinding.TodayScreenTitleItemBinding
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


class TodayScreenAdapter(
    private var items: List<TodayScreenData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val forecastTodayScreen = 1
    private val titleTodayScreen = 0


    class TodayForecastCardViewHolder(private val rowCardForecastItemBinding: TodayCardForecastItemBinding) :
        RecyclerView.ViewHolder(rowCardForecastItemBinding.root) {
        fun bind(card: TodayScreenData.ForecastData) {
            rowCardForecastItemBinding.tvTodayHour.text = itemView.context.getString(R.string.tv_time, card.todayCardInfo.date.hour)
            rowCardForecastItemBinding.ivTodayIcon.setImageResource(DataObject.weatherIcon(card.todayCardInfo.weather))
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
        fun bind(title: TodayScreenData.TodayTitleObject) {
            "${title.title.city}, ${title.title.region}".also {
                todayScreenTitleItemBinding.tvTodayLocation.text = it
            }

            val todayDateTime = title.title.date
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

            is TodayForecastCardViewHolder -> holder.bind(items[position] as TodayScreenData.ForecastData)
            is TodayTitleViewHolder -> holder.bind(items[position] as TodayScreenData.TodayTitleObject)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {

            is TodayScreenData.TodayTitleObject -> titleTodayScreen
            is TodayScreenData.ForecastData -> forecastTodayScreen

        }
    }
}