package co.develhope.meteoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentSearchBinding
import co.develhope.meteoapp.network.Repository
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.ui.adapter.SearchPlaceAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val newArrayList : List<Place>  = listOf<Place>(
    Place("Milano", 0.0, 0.0, "Lombardia"),
    Place("Bergamo", 0.0, 0.0, "Lombardia"),
    Place("Catania", 0.0, 0.0, "Sicilia"),
    Place("Siragusa", 0.0, 0.0, "Sicilia"),
    )

    private lateinit var recycleView : RecyclerView

    private var _binding: FragmentSearchBinding? = null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recycleView = view.findViewById(R.id.item_list)
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        recycleView.adapter = SearchPlaceAdapter(newArrayList)


        lifecycleScope.launch {
            try {
                //prender e irisultati e passarli all'adapter
                Repository().getSearchDetails("")

            } catch (e: Exception) {
                Log.e("SearchFragment", "Error: ${e.message}")
            }
        }
    }
}

