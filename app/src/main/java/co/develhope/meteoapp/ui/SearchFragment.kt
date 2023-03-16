package co.develhope.meteoapp.ui

import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentSearchBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.DataObject.getSearchCity
import co.develhope.meteoapp.network.Repository
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.mapping.toHomeCards
import co.develhope.meteoapp.ui.adapter.SearchPlaceAdapter
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter = SearchPlaceAdapter(getSearchCity()) {
        DataObject.cityName = it.name
        DataObject.cityCountry = it.region
        DataObject.cityLatitude = it.latitude
        DataObject.cityLongitude = it.longitude
        findNavController().navigate(R.id.homeFragment)

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
        binding.itemList.layoutManager = layoutManager
        binding.itemList.adapter = searchAdapter


        callingApi("Palermo")

    }

    private fun callingApi(userSearch: String) {

        lifecycleScope.launch {
            try {
                //prendere i risultati e passarli all'adapter
                RetrofitInstance().getPlaces(userSearch)
                val response =
                    RetrofitInstance().serviceGeoCodingApi.getDayEndPointDetails(DataObject.cityName)
                        .toDomain()



            } catch (e: Exception) {
                Log.e("SearchFragment", "Error: ${e.message}")
            }
        }
    }
}








