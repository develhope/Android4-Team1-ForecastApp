package co.develhope.meteoapp.Data


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R


class HomeAdapter(
    val list: List<RecyclerElement>,
    val onClick: (RecyclerElement?) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = list.getOrNull(position)
        if (element != null) {
            holder.itemDay.text = element.day
            holder.itemDataDay.text = element.dataDay
            holder.itemMax.text = element.max
            holder.itemMin.text = element.min
            //holder.itemMin.text = holder.itemMin.context.getString(element.min) quando saranno parametrizzati
            holder.itemRain.text = element.rain
            holder.itemWind.text = element.wind
            holder.itemDegree.text = element.degree
            holder.itemDegree2.text = element.degree2
            holder.itemDataPercent.text = element.dataPercent
            holder.itemDataKMH.text = element.dataKMH
            element.icon.let { holder.itemIcon.setImageResource(it) }

            holder.itemView2.setOnClickListener {
                onClick.invoke(element)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val itemView2: View) : RecyclerView.ViewHolder(itemView2) {
        var itemDay: TextView = itemView2.findViewById(R.id.day)
        var itemDataDay: TextView = itemView2.findViewById(R.id.data)
        var itemMin: TextView = itemView2.findViewById(R.id.min)
        var itemMax: TextView = itemView2.findViewById(R.id.max)
        var itemRain: TextView = itemView2.findViewById(R.id.rain)
        var itemWind: TextView = itemView2.findViewById(R.id.data6)
        var itemIcon: ImageView = itemView2.findViewById(R.id.sun)
        var itemDegree: TextView = itemView2.findViewById(R.id.degree)
        var itemDegree2: TextView = itemView2.findViewById(R.id.degree2)
        var itemDataPercent: TextView = itemView2.findViewById(R.id.dataPercent)
        var itemDataKMH: TextView = itemView2.findViewById(R.id.dataKMH)
    }
}

data class RecyclerElement(
    val day: String, //saranno tutti Int parametrizzati es: R.string/day
    val dataDay: String,
    val min: String,
    val max: String,
    val rain: String,
    val wind: String,
    val icon: Int,
    val degree: String,
    val degree2: String,
    val dataPercent: String,
    val dataKMH: String,
)

