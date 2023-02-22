package co.develhope.meteoapp.tomorrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Data.DataObject
import co.develhope.meteoapp.databinding.FragmentTomorrowBinding
import java.time.OffsetDateTime

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

    private val titleTomorrowScreen = DataObject.TomorrowTitle("Palermo","Sicilia", OffsetDateTime.now())

    private val rowTomorrowScreen = DataObject.TomorrowRow(OffsetDateTime.now(),14,80,15,1/10,20,"NNE 1km/H",3,0)
    private val rowTomorrowScreen2 = DataObject.TomorrowRow(OffsetDateTime.now().plusHours(1),15,80,13,2/10,50,"NNE 1km/h",5,10)
    private val rowTomorrowScreen3 = DataObject.TomorrowRow(OffsetDateTime.now().plusHours(2),16,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen4 = DataObject.TomorrowRow(OffsetDateTime.now().plusHours(3),16,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen5 = DataObject.TomorrowRow(OffsetDateTime.now().plusHours(4),16,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen6 = DataObject.TomorrowRow(OffsetDateTime.now().plusHours(5),17,80,12,1/10,80,"NNE 1km/h",3,0)
    private val rowTomorrowScreen7 = DataObject.TomorrowRow(OffsetDateTime.now().plusHours(6),17,80,12,1/10,80,"NNE 1km/h",3,0)

    private val tomorrowItems = listOf(
        DataObject.TomorrowSealed.Title(titleTomorrowScreen),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen2),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen3),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen4),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen5),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen6),
        DataObject.TomorrowSealed.Row(rowTomorrowScreen7),

    )

}