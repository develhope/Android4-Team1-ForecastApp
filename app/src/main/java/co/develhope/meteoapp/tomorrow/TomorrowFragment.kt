package co.develhope.meteoapp.tomorrow

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Data.Data
import co.develhope.meteoapp.databinding.FragmentTomorrowBinding
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

class TomorrowFragment : Fragment() {

    private var myBinding: FragmentTomorrowBinding? = null
    private val binding get() = myBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myBinding = FragmentTomorrowBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.tomorrowRecyclerView.layoutManager = layoutManager
        binding.tomorrowRecyclerView.setHasFixedSize(true)
        binding.tomorrowRecyclerView.adapter = TomorrowAdapter(tomorrowItems)


    }

    private val titleTomorrowScreen = Data.TomorrowTitle("Palermo","Sicilia", OffsetDateTime.now())

    private val rowTomorrowScreen = Data.TomorrowRow(OffsetDateTime.now(),14,80,15,1/10,20,"NNE 1km/H",3,0)
    private val rowTomorrowScreen2 = Data.TomorrowRow(OffsetDateTime.now().plusHours(1),15,80,13,2/10,50,"NNE 1km/h",5,10)
    private val rowTomorrowScreen3 = Data.TomorrowRow(OffsetDateTime.now().plusHours(2),16,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen4 = Data.TomorrowRow(OffsetDateTime.now().plusHours(3),16,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen5 = Data.TomorrowRow(OffsetDateTime.now().plusHours(4),16,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen6 = Data.TomorrowRow(OffsetDateTime.now().plusHours(5),17,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen7 = Data.TomorrowRow(OffsetDateTime.now().plusHours(6),17,80,12,1/10,80,"NNE 1km/h",3,0)

    private val tomorrowItems = listOf(
        Data.TomorrowSealed.Title(titleTomorrowScreen),
        Data.TomorrowSealed.Row(rowTomorrowScreen),
        Data.TomorrowSealed.Row(rowTomorrowScreen2),
        Data.TomorrowSealed.Row(rowTomorrowScreen3),
        Data.TomorrowSealed.Row(rowTomorrowScreen4),
        Data.TomorrowSealed.Row(rowTomorrowScreen5),
        Data.TomorrowSealed.Row(rowTomorrowScreen6),
        Data.TomorrowSealed.Row(rowTomorrowScreen7),

    )

}