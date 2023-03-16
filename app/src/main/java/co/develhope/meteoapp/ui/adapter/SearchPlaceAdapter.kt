package co.develhope.meteoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.R
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem

class HourlyItemViewHolder ( view : View) : ViewHolder(view){

    val degrees : TextView
    val weather : TextView
    val city : TextView
    init {

        degrees = view.findViewById(R.id.grade)
        weather = view.findViewById(R.id.wather_status)
        city = view.findViewById(R.id.city_name)
    }
}

class SearchPlaceAdapter(private val cardList: List<HourlyItem>, private val onClick: (Place) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cardview, parent, false)
        return HourlyItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        (holder as HourlyItemViewHolder).weather.text =
            (cardList[position] ).weather.toString().lowercase()
    holder.itemView.setOnClickListener {

        onClick((cardList[position]).city)
    }


        holder.degrees.text =
           "${ (cardList[position]).degrees }°"
        holder.city.text = (cardList[position]).city.name
    }
}

















