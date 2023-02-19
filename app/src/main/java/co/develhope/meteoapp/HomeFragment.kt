package co.develhope.meteoapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.Data.HomeAdapter
import co.develhope.meteoapp.Data.RecyclerElement
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private var adapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
        val calendar = Calendar.getInstance()

        view.findViewById<TextView>(R.id.day).text = "Oggi"
        view.findViewById<TextView>(R.id.city).text = "Palermo, Sicilia"
        view.findViewById<TextView>(R.id.degree).text = "13°"
        view.findViewById<TextView>(R.id.degree2).text = "26°"
        view.findViewById<TextView>(R.id.dataPercent).text = "3%"
        view.findViewById<TextView>(R.id.dataKMH).text = "20kmh"
        view.findViewById<TextView>(R.id.data).text = dateFormat.format(calendar.time)


        fun getIconSun(weather : String ) : Int{
            return if (weather == "sunny"){
                R.drawable.ic_launcher_foreground
            } else{
                R.drawable.sun_icon
            }
        }


        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val list = mutableListOf<RecyclerElement>()
        for (i in 0 until 5) {
            val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
            val capitalizedDayOfWeek = dayOfWeek.replaceFirstChar { it.uppercase() }
            val recyclerElement = RecyclerElement(
                capitalizedDayOfWeek,
                dateFormat.format(calendar.time),
                "min",
                "max",
                "precip.",
                "vento",
                getIconSun("cloudy"),
                "20°",
                "31°",
                "0%",
                "12kmh")
            list.add(recyclerElement)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }



        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter(list) {
            // GESTIONE DEL CLICK DA IMPREMENTARE
        }
        recyclerView.adapter = adapter
    }

}

