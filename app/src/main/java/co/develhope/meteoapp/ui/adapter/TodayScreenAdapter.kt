package co.develhope.meteoapp.ui.adapter


import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.TodayCardForecastItemBinding
import co.develhope.meteoapp.databinding.TodayScreenTitleItemBinding
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


class TodayScreenAdapter(
    private var items: List<TodayScreenData>,
    val itemOpened: MutableList<TomorrowSealed.Row> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val forecastTodayScreen = 1
    private val titleTodayScreen = 0


    class TodayForecastCardViewHolder(val rowCardForecastItemBinding: TodayCardForecastItemBinding) :
        RecyclerView.ViewHolder(rowCardForecastItemBinding.root) {
        fun bind(card: TodayScreenData.ForecastData,  position: Int?=null) {

            rowCardForecastItemBinding.tvTodayHour.text =
                itemView.context.getString(R.string.tv_time, card.todayCardInfo.date.hour)
            rowCardForecastItemBinding.ivTodayIcon.setImageResource(card.todayCardInfo.iconToday)
            card.todayCardInfo.temperature.also {
                rowCardForecastItemBinding.tvTodayTemperature.text = it
            }
            card.todayCardInfo.precipitation.also {
                rowCardForecastItemBinding.tvTodayHumidity.text = it
            }
            card.todayCardInfo.perc_temperature.also {
                rowCardForecastItemBinding.tvPerceivedTemperature.text = it
            }

            rowCardForecastItemBinding.tvIndexValue.text = card.todayCardInfo.UV_Index.toString()

            card.todayCardInfo.humidity.also {
                rowCardForecastItemBinding.tvHumidityValue.text = it
            }
            card.todayCardInfo.wind.also {
                rowCardForecastItemBinding.tvWindValue.text = it
            }
            card.todayCardInfo.coverage.also {
                rowCardForecastItemBinding.tvCoverageValue.text = it
            }
            card.todayCardInfo.rain.also { rowCardForecastItemBinding.tvRainValue.text = it }

            rowCardForecastItemBinding.ivWaterDrop.setImageResource(R.drawable.water_drop)
            rowCardForecastItemBinding.toggle.setImageResource(R.drawable.toggle_icon_up)


            rowCardForecastItemBinding.toggle.setOnClickListener {
                if (rowCardForecastItemBinding.cvTodayCard.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        rowCardForecastItemBinding.cvTodayCard,
                        AutoTransition()

                    )
                    rowCardForecastItemBinding.cvTodayCard.visibility = View.VISIBLE
                    rowCardForecastItemBinding.myView.visibility = View.GONE
                    rowCardForecastItemBinding.toggle.rotation = 180F

                } else {
                    TransitionManager.beginDelayedTransition(
                        rowCardForecastItemBinding.cvTodayCard,
                        AutoTransition()
                    )
                    rowCardForecastItemBinding.cvTodayCard.visibility = View.GONE
                    rowCardForecastItemBinding.myView.visibility = View.VISIBLE
                    rowCardForecastItemBinding.toggle.rotation = 0F


                }
            }

        }
    }

    class TodayTitleViewHolder(private val todayScreenTitleItemBinding: TodayScreenTitleItemBinding) :
        RecyclerView.ViewHolder(todayScreenTitleItemBinding.root) {
        fun bind(title: TodayScreenData.TodayTitleObject) {
            "${title.title.city} ${title.title.region}".also {
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
            is TodayForecastCardViewHolder -> {
                holder.bind(items.getOrNull(position) as TodayScreenData.ForecastData)
                if (position == 1)
                    holder.rowCardForecastItemBinding.toggle.performClick()
            }
            is TodayTitleViewHolder -> holder.bind(items.getOrNull(position) as TodayScreenData.TodayTitleObject)
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