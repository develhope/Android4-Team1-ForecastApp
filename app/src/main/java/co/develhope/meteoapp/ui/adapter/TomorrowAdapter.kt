package co.develhope.meteoapp.ui.adapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ItemTomorrowRowBinding
import co.develhope.meteoapp.databinding.ItemTomorrowTitleBinding
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


class TomorrowAdapter(
    private val item: List<TomorrowSealed>,
    val day: Int?,
    val itemOpened: MutableList<TomorrowSealed.Row> = mutableListOf() //impostiamo una mut list di row, mi serve a salvare gli elementi che saranno poi aperti
) :  //aggiungiamo la lista al costruttore così possiamo passargliela quando creiamo l'adapter
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val typeTitle = 0
    private val typeRow = 1

    inner class TitleTomorrowViewHolder(private val titleBinding: ItemTomorrowTitleBinding) :
        RecyclerView.ViewHolder(titleBinding.root) {
        fun bind(title: TomorrowSealed.Title) {
            titleBinding.city.text = title.titleTomorrow.city
            titleBinding.region.text = title.titleTomorrow.region

            titleBinding.tomorrow.text = when (day) {
                1 -> "Domani"
                else -> OffsetDateTime.now().plusDays(day?.toLong() ?: 0)
                    .format(DateTimeFormatter.ofPattern("EEEE"))
            }


            val dateTime = title.titleTomorrow.day
            val offsetDateTime = OffsetDateTime.ofInstant(dateTime.toInstant(), ZoneOffset.UTC)
            val formattedDate =
                DateTimeFormatter.ofPattern("dd MMMM yyyy")
                    .format(offsetDateTime.plusDays(day?.toLong() ?: 0L))
            titleBinding.day.text = formattedDate

        }
    }


    inner class RowTomorrowViewHolder(private val rowBinding: ItemTomorrowRowBinding) : //innerclass per accedere alla lista
        RecyclerView.ViewHolder(rowBinding.root) {
        fun bind(row: TomorrowSealed.Row, position: Int) {//aggiungiamo position perchè ci serve nel notify sotto

            if (row in itemOpened) { //se l'elemento si trova dentro questa lista significa che è aperto)
                TransitionManager.beginDelayedTransition(
                    rowBinding.expandable,
                    AutoTransition()
                )
                rowBinding.expandable.visibility = View.VISIBLE
                rowBinding.myView.visibility = View.GONE
                rowBinding.toggle.rotation = 180F
            } else { //altrimenti è un elemento chiuso
                TransitionManager.beginDelayedTransition(
                    rowBinding.expandable,
                    AutoTransition()
                )
                rowBinding.expandable.visibility = View.GONE
                rowBinding.myView.visibility = View.VISIBLE
                rowBinding.toggle.rotation = 0F
            }

            //Row Elements
            rowBinding.ivMoon.setImageResource(row.tomorrowRow.iconTomorrow)
            rowBinding.ivWaterDrop.setImageResource(R.drawable.water_drop)
            rowBinding.degrees.text =
                row.tomorrowRow.degrees
            rowBinding.time.text =
                itemView.context.getString(R.string.tv_time, row.tomorrowRow.time.hour)
            rowBinding.percentage.text =
                row.tomorrowRow.percentage
            rowBinding.toggle.setImageResource(R.drawable.toggle_icon_up)

            //Card Elements
            rowBinding.cvDegrees.text = row.tomorrowRow.cvDegrees
            rowBinding.cvNumberUV.text = row.tomorrowRow.cvNumberUV
            rowBinding.cvPercentage2.text = row.tomorrowRow.cvPercentage2
            rowBinding.cvPercentage.text = row.tomorrowRow.cvPercentage
            rowBinding.cvRainCm.text =
                row.tomorrowRow.cvRainCM
            rowBinding.NNE.text = row.tomorrowRow.cvNNE

            rowBinding.toggle.setOnClickListener {
                if (row in itemOpened) { //la row è nella lista, significa che è aperta e quindi la togliamo dalla lista per chiuderla
                    itemOpened.remove(row)
                } else { //se non è nella lista la aggiungiamo così si apre
                    itemOpened.add(row)
                }

                notifyItemChanged(position) //diciamo all'adapter che è cambiato qualcosa così aggiorna l'elemento alla position data
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (item[position]) {
            is TomorrowSealed.Title -> typeTitle
            is TomorrowSealed.Row -> typeRow
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            typeTitle -> TitleTomorrowViewHolder(
                ItemTomorrowTitleBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            typeRow -> RowTomorrowViewHolder(
                ItemTomorrowRowBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            else -> throw java.lang.IllegalArgumentException("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleTomorrowViewHolder -> holder.bind(item[position] as TomorrowSealed.Title)
            is RowTomorrowViewHolder -> holder.bind(item[position] as TomorrowSealed.Row, position)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

}


