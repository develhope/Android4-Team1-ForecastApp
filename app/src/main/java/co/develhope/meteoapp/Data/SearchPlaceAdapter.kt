package co.develhope.meteoapp.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R

class SearchPlaceAdapter(private val cardList: List<DataSearchFrag>) : RecyclerView.Adapter<SearchPlaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_cardview, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = cardList[position]
        holder.cityName.text = currentItem.cityList
        holder.cityWeather.text = currentItem.weather
        holder.cityGrade.text = currentItem.dataList.toString()
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val cityName : TextView = itemView.findViewById(R.id.city_name)
        val cityGrade : TextView = itemView.findViewById(R.id.grade)
        val cityWeather : TextView = itemView.findViewById(R.id.wather_status)
    }
}





