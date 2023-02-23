package co.develhope.meteoapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Data.*
import co.develhope.meteoapp.Home.HomeCards
import co.develhope.meteoapp.Home.HomeScreenElements
import co.develhope.meteoapp.Home.Next5Days
import co.develhope.meteoapp.Home.Title
import co.develhope.meteoapp.databinding.FragmentHomeBinding
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
                "Today" -> this.findNavController().navigate(R.id.oggiFragment)
                "Tomorrow" -> this.findNavController().navigate(R.id.domaniFragment)
            }
        })

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
                    "0%",
                    "12kmh",
                    "Today"
                )
            ),
            HomeScreenElements.SubTitleHome(Next5Days("NEXT 5 DAYS")),
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
                    "0%",
                    "20kmh",
                    "Tomorrow"
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
                    "10%",
                    "10kmh",
                    "2day"
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
                    "0%",
                    "5kmh",
                    "3day"
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
                    "0%",
                    "6kmh",
                    "4day"
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
                    "0%",
                    "11kmh",
                    "5day"
                )
            )

        )
    }


}


