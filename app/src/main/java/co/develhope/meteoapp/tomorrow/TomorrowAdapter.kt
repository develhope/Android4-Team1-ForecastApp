package co.develhope.meteoapp.tomorrow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.Data.DataObject
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ItemTomorrowRowBinding
import co.develhope.meteoapp.databinding.ItemTomorrowTitleBinding
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class TomorrowAdapter(private val item: List<DataObject.TomorrowSealed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val typeTitle = 0
    private val typeRow = 1

    class TitleTomorrowViewHolder(private val titleBinding: ItemTomorrowTitleBinding) :
        RecyclerView.ViewHolder(titleBinding.root) {
            fun bind (title: DataObject.TomorrowSealed.Title) {

                titleBinding.city.text = title.titleTomorrow.city
                titleBinding.region.text = title.titleTomorrow.region

                val dateTime = title.titleTomorrow.day
                val offsetDateTime = OffsetDateTime.ofInstant(dateTime.toInstant(),ZoneOffset.UTC)
                val formattedDate = DateTimeFormatter.ofPattern("dd MMMM yyyy").format(offsetDateTime.plusDays(1))
                titleBinding.day.text = formattedDate
            }
        }


    class RowTomorrowViewHolder(private val rowBinding: ItemTomorrowRowBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
            fun bind(row: DataObject.TomorrowSealed.Row) {

                //Row Elements
                rowBinding.ivMoon.setImageResource(R.drawable.crescent_moon)
                rowBinding.ivWaterDrop.setImageResource(R.drawable.water_drop)
                rowBinding.degrees.text = itemView.context.getString(R.string.tv_temperature,row.tomorrowRow.degrees)
                rowBinding.time.text = itemView.context.getString(R.string.tv_time,row.tomorrowRow.time.hour)
                rowBinding.percentage.text = itemView.context.getString(R.string.tv_percentage,row.tomorrowRow.percentage)

                //Card Elements
                rowBinding.cvDegrees.text = itemView.context.getString(R.string.tv_temperature,row.tomorrowRow.cvDegrees)
                rowBinding.cvNumberUV.text = itemView.context.getString(R.string.tv_UV_index,row.tomorrowRow.cvNumberUV)
                rowBinding.cvPercentage2.text = itemView.context.getString(R.string.tv_percentage,row.tomorrowRow.cvPercentage2)
                rowBinding.cvPercentage.text = itemView.context.getString(R.string.tv_percentage,row.tomorrowRow.cvPercentage)
                rowBinding.cvRainCm.text = itemView.context.getString(R.string.tv_rain,row.tomorrowRow.cvRainCM)
                rowBinding.NNE.text = row.tomorrowRow.cvNNE
            }
        }

    override fun getItemViewType(position: Int): Int {
        return when (item[position]){
            is DataObject.TomorrowSealed.Title -> typeTitle
            is DataObject.TomorrowSealed.Row -> typeRow
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            typeTitle -> TitleTomorrowViewHolder(ItemTomorrowTitleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            typeRow -> RowTomorrowViewHolder(ItemTomorrowRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

            else -> throw java.lang.IllegalArgumentException("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is TitleTomorrowViewHolder -> holder.bind(item[position] as DataObject.TomorrowSealed.Title)
            is RowTomorrowViewHolder -> holder.bind(item[position] as DataObject.TomorrowSealed.Row)
        }

        
    }

    override fun getItemCount(): Int {
        return item.size
    }

}


