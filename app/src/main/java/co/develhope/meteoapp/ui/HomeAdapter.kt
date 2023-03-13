package co.develhope.meteoapp.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.CardLayoutHomeBinding
import co.develhope.meteoapp.databinding.HomeSubTitleBinding
import co.develhope.meteoapp.databinding.HomeTitleCityBinding
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField.*
import java.util.*


class HomeAdapter(
    private val list: List<HomeScreenElements>, private val onClick: (HomeScreenEvents) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (list.getOrNull(position)) {
            is HomeScreenElements.TitleHome -> 1
            is HomeScreenElements.SubTitleHome -> 2
            is HomeScreenElements.CardsHome -> 3
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when (viewType) {
            1 -> TitleViewHolder(
                HomeTitleCityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            2 -> SubTitleViewHolder(
                HomeSubTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            3 -> CardViewHolder(
                CardLayoutHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else ->
                object : HomeViewHolder(View(parent.context)) {
                    override fun onBind(
                        elements: HomeScreenElements,
                        onClick: (HomeScreenEvents) -> Unit
                    ) {
                    }
                }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val element = list.getOrNull(position)
        if (element != null) {
            holder.onBind(element, onClick)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

abstract class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(elements: HomeScreenElements, onClick: (HomeScreenEvents) -> Unit)
}

class CardViewHolder(val binding: CardLayoutHomeBinding) : HomeViewHolder(binding.root) {

    override fun onBind(elements: HomeScreenElements, onClick: (HomeScreenEvents) -> Unit) {
        if (elements is HomeScreenElements.CardsHome) {
            if (elements.cardsHome.day != null) {
                binding.data.text = DateTimeFormatterBuilder()
                    .appendText(DAY_OF_MONTH)
                    .appendLiteral("/")
                    .appendValue(MONTH_OF_YEAR, 2)
                    .toFormatter(Locale.getDefault())
                    .format(elements.cardsHome.day)

            } else {
                binding.day.text = ""
                binding.data.text = ""
            }

            fun setDayHomeCards() {
                return when (elements.cardsHome.key) {
                    HomeScreenEvents.Today -> binding.day.text =
                        itemView.context.getString(R.string.Today)
                    HomeScreenEvents.Tomorrow -> binding.day.text =
                        itemView.context.getString(R.string.Tomorrow)
                    else -> {
                        binding.day.text = DateTimeFormatterBuilder()
                            .appendText(DAY_OF_WEEK)
                            .toFormatter(Locale.getDefault())
                            .format(elements.cardsHome.day)
                            .replaceFirstChar(Char::titlecase)
                    }
                }
            }


            setDayHomeCards()
            binding.min.text = binding.min.context.getString(R.string.min)
            binding.max.text = binding.max.context.getString(R.string.max)
            binding.rain.text = binding.rain.context.getString(R.string.precip)
            binding.data6.text = binding.data6.context.getString(R.string.vento_home)
            binding.degree.text = elements.cardsHome.degree
            binding.degree2.text = elements.cardsHome.degree2
            binding.dataPercent.text = elements.cardsHome.dataPercent
            binding.dataKMH.text = elements.cardsHome.dataKMH
            elements.cardsHome.icon.let { binding.sun.setImageResource(it) }

            binding.cardView.setOnClickListener {
                when (elements.cardsHome.key) {
                    HomeScreenEvents.Today -> onClick(HomeScreenEvents.Today)
                    HomeScreenEvents.Tomorrow -> onClick(HomeScreenEvents.Tomorrow)
                    else -> onClick(HomeScreenEvents.OtherDay())
                }
            }
        }
    }
}

class TitleViewHolder(val binding: HomeTitleCityBinding) : HomeViewHolder(binding.root) {
    override fun onBind(elements: HomeScreenElements, onClick: (HomeScreenEvents) -> Unit) {
        if (elements is HomeScreenElements.TitleHome) {
            binding.city.text = elements.title.city
        }
    }
}

class SubTitleViewHolder(val binding: HomeSubTitleBinding) : HomeViewHolder(binding.root) {
    override fun onBind(elements: HomeScreenElements, onClick: (HomeScreenEvents) -> Unit) {
        if (elements is HomeScreenElements.SubTitleHome) {
            binding.next5Days.text = elements.subTileHome.next5Days
        }
    }
}