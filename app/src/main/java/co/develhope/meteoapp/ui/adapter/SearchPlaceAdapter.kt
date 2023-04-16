package co.develhope.meteoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import co.develhope.meteoapp.R
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import okhttp3.internal.notify


class SearchPlaceAdapter(
    private var cardList: List<HourlyItem>,
    private val onClick: (HourlyItem?) -> Unit
) : RecyclerView.Adapter<SearchPlaceAdapter.HourlyItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HourlyItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_cardview, parent, false)
        )


    override fun getItemCount(): Int {
        return cardList.size
    }


    override fun onBindViewHolder(holder: HourlyItemViewHolder, position: Int) {

        val model = cardList.getOrNull(position)
       // holder.weather.text =
      //      model?.weather.toString().lowercase()
        holder.itemView.setOnClickListener {

            onClick(model)
        }


       holder.degrees.text = "${model?.degrees}"
        holder.city.text = "${model?.city?.name}, ${model?.city?.region}"
    }

    fun setNewList(listNew : List<HourlyItem>){
        if(listNew != cardList){
            cardList = listNew
            notifyDataSetChanged()
        }

    }

    class HourlyItemViewHolder(view: View) : ViewHolder(view) {

        val degrees: TextView
        val weather: TextView
        val city: TextView

        init {

            degrees = view.findViewById(R.id.grade)
            weather = view.findViewById(R.id.wather_status)
            city = view.findViewById(R.id.city_name)
        }
    }
}

















