package co.develhope.meteoapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Data.Data
import co.develhope.meteoapp.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {
    private var baseContainerBinding: FragmentHomeBinding? = null
    private val binding get() = baseContainerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseContainerBinding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = HomeAdapter(getListAdapter()) {
            // GESTIONE DEL CLICK DA IMPREMENTARE
        }

    }


    fun getIconSun(weather : String ) : Int{
        return if (weather == "sunny"){
            R.drawable.ic_launcher_foreground
        } else{
            R.drawable.sun_icon
        }
    }

    fun getListAdapter() : List<Data.HomeScreenElements>{
        return listOf(
            Data.HomeScreenElements.Title("Palermo", "Sicilia"),
            Data.HomeScreenElements.HomeCards(null,
                null,
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh"),
            Data.HomeScreenElements.Next5Days("next 5 days"),
            Data.HomeScreenElements.HomeCards(null,
                null,
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh"),
            Data.HomeScreenElements.HomeCards(null,
                null,
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh"),
            Data.HomeScreenElements.HomeCards(null,
                null,
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh"),
            Data.HomeScreenElements.HomeCards(null,
                null,
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh"),
            Data.HomeScreenElements.HomeCards(null,
                null,
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh")

        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseContainerBinding = null
    }

}


