package co.develhope.meteoapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Data.DataObject
import co.develhope.meteoapp.Home.*
import co.develhope.meteoapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class HomeFragment : Fragment() {
    private var baseContainerBinding: FragmentHomeBinding? = null
    private val binding get() = baseContainerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseContainerBinding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = HomeAdapter(getListAdapter(), onClick = {
            when (it) {
                HomeScreenEvents.Today -> this.findNavController()
                    .navigate(R.id.oggiFragment)
                HomeScreenEvents.Tomorrow -> this.findNavController()
                    .navigate(R.id.domaniFragment)
                HomeScreenEvents.OtherDay() -> Toast.makeText(
                    context,
                    "da implementare",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {Toast.makeText(
                    context,
                    "error",
                    Toast.LENGTH_SHORT).show()}
            }

        })


        lifecycleScope.launch {
            try {
                //RetrofitInstanceApiOpenMeteo.getDayDetails() metti la nuova funzione
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error: ${e.message}")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseContainerBinding = null
    }

    fun getListAdapter(): List<HomeScreenElements> {
        return listOf(
            HomeScreenElements.TitleHome(Title("Palermo, Sicilia")),
            HomeScreenElements.CardsHome(
                HomeCards(
                    OffsetDateTime.now(),
                    "min",
                    "max",
                    "precip.",
                    "vento",
                    DataObject.weatherIcon(Weather.SUNNY),
                    "20°",
                    "31°",
                    "0mm",
                    "12kmh",
                    key = HomeScreenEvents.Today
                )
            ),
            HomeScreenElements.SubTitleHome(Next5Days("PROSSIMI 5 GIORNI")),
            HomeScreenElements.CardsHome(
                HomeCards(
                    OffsetDateTime.now().plusDays(1),
                    "min",
                    "max",
                    "precip.",
                    "vento",
                    DataObject.weatherIcon(Weather.SUNNY),
                    "18°",
                    "29°",
                    "0mm",
                    "20kmh",
                    key = HomeScreenEvents.Tomorrow
                )
            ),
            HomeScreenElements.CardsHome(
                HomeCards(
                    OffsetDateTime.now().plusDays(2),
                    "min",
                    "max",
                    "precip.",
                    "vento",
                    DataObject.weatherIcon(Weather.SUNNY),
                    "21°",
                    "30°",
                    "10mm",
                    "10kmh",
                    key = HomeScreenEvents.OtherDay(3)
                )
            ),
            HomeScreenElements.CardsHome(
                HomeCards(
                    OffsetDateTime.now().plusDays(3),
                    "min",
                    "max",
                    "precip.",
                    "vento",
                    DataObject.weatherIcon(Weather.SUNNY),
                    "22°",
                    "31°",
                    "0mm",
                    "5kmh",
                    key = HomeScreenEvents.OtherDay(4)
                )
            ),
            HomeScreenElements.CardsHome(
                HomeCards(
                    OffsetDateTime.now().plusDays(4),
                    "min",
                    "max",
                    "precip.",
                    "vento",
                    DataObject.weatherIcon(Weather.SUNNY),
                    "10°",
                    "21°",
                    "0mm",
                    "6kmh",
                    key = HomeScreenEvents.OtherDay(5)
                )
            ),
            HomeScreenElements.CardsHome(
                HomeCards(
                    OffsetDateTime.now().plusDays(5),
                    "min",
                    "max",
                    "precip.",
                    "vento",
                    DataObject.weatherIcon(Weather.SUNNY),
                    "25°",
                    "30°",
                    "0mm",
                    "11kmh",
                    key = HomeScreenEvents.OtherDay(6)
                )
            )

        )
    }
}


