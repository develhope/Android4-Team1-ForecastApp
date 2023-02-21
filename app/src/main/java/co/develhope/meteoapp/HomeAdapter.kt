package co.develhope.meteoapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.Data.Data
import co.develhope.meteoapp.databinding.CardLayoutHomeBinding
import co.develhope.meteoapp.databinding.HomeSubTitleBinding
import co.develhope.meteoapp.databinding.HomeTitleCityBinding


class HomeAdapter(
    val list: List<Data.HomeScreenElements>,
    val onClick: (Data.HomeScreenElements?) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (list.getOrNull(position)) {
            is Data.HomeScreenElements.Title -> 1
            is Data.HomeScreenElements.Next5Days -> 2
            is Data.HomeScreenElements.HomeCards -> 3
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
                    override fun onBind(elements: Data.HomeScreenElements) {
                    }
                }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val element = list.getOrNull(position)
        if (element != null) {
            holder.onBind(element)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

abstract class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(elements: Data.HomeScreenElements)
}

class CardViewHolder(val binding: CardLayoutHomeBinding) : HomeViewHolder(binding.root) {
    override fun onBind(elements: Data.HomeScreenElements) {
        if (elements is Data.HomeScreenElements.HomeCards) {
            //binding.day.text = elements.day
            //binding.data.text = elements.dataDay
            binding.max.text = elements.max
            binding.min.text = elements.min
            //binding.itemMin.text = binding.itemMin.context.getString(element.min) quando saranno parametrizzati
            binding.rain.text = elements.rain
            binding.data6.text = elements.wind
            binding.degree.text = elements.degree
            binding.degree2.text = elements.degree2
            binding.dataPercent.text = elements.dataPercent
            binding.dataKMH.text = elements.dataKMH
            elements.icon.let { binding.sun.setImageResource(it) }
        }

        /*binding.itemView2.setOnClickListener {
            onClick.invoke(element)
        }*/
    }
}

class TitleViewHolder(val binding: HomeTitleCityBinding) : HomeViewHolder(binding.root) {
    override fun onBind(elements: Data.HomeScreenElements) {
        if (elements is Data.HomeScreenElements.Title) {
            binding.city.text = elements.city
        }
    }
}

class SubTitleViewHolder(val binding: HomeSubTitleBinding) : HomeViewHolder(binding.root) {
    override fun onBind(elements: Data.HomeScreenElements) {
        if (elements is Data.HomeScreenElements.Next5Days) {
            binding.next5Days.text = elements.next5Days
        }
    }
}