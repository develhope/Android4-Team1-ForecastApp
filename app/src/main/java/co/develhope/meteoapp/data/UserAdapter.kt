package co.develhope.meteoapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentCardviewBinding
import co.develhope.meteoapp.databinding.FragmentSearchBinding

class UserAdapter(private val cardList: List<DataSearchFrag>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context;


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





