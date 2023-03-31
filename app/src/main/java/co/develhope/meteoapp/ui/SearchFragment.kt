package co.develhope.meteoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentSearchBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.DataObject.getSearchCity
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.mapping.toTodayCardInfo
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import co.develhope.meteoapp.ui.adapter.SearchPlaceAdapter
import co.develhope.meteoapp.ui.adapter.TodayScreenAdapter
import co.develhope.meteoapp.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModelSearch : SearchViewModel by viewModels()
    private val searchAdapter = SearchPlaceAdapter(getSearchCity()) {
        if(it != null) {
            DataObject.setSelectedCity(it)
            findNavController().navigate(R.id.homeFragment)
        }
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


        binding.frame48.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //do nothings
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                manageView()
                viewModelSearch.apiCallResultToday(newText.orEmpty())

                viewModelSearch.response.observe(viewLifecycleOwner) { response ->
                    when (response) {
                        is ApiResponse.Loading -> {
                            binding.loadingView.visibility = View.VISIBLE
                        }
                        is ApiResponse.Success -> {
                            val hourlyItems = response.body!!.map { HourlyItem(city = it, degrees = null, weather = null) }
                            val adapter = TodayScreenAdapter(items = hourlyItems)
                            binding.itemList.adapter = adapter
                            binding.loadingView.visibility = View.GONE
                        }
                        is ApiResponse.Error -> {
                            binding.loadingView.visibility = View.GONE
                            Log.e("TodayFragment", "Error")
                            this@SearchFragment.findNavController().navigate(R.id.errorFragment)
                        }
                        else -> {
                            binding.loadingView.visibility = View.GONE
                            Log.e("TodayFragment", "Error")
                            this@SearchFragment.findNavController().navigate(R.id.errorFragment)
                        }
                    }
                }

            }
                return true
            }

        })
    }

    fun manageView(){
        binding.ricercheRecenti.isVisible = binding.frame48.query.isEmpty()
    }


    private fun callingApi(userSearch: String) {

        lifecycleScope.launch {
            try {
                RetrofitInstance().getPlaces(userSearch)
                val response =
                    RetrofitInstance().serviceGeoCodingApi.getDayEndPointDetails(userSearch)
                        .toDomain()
                    searchAdapter.setNewList(response.map { HourlyItem(city = it, degrees = null, weather = null) })

            } catch (e: Exception) {
                Log.e("SearchFragment", "Error: ${e.message}")
            }
        }
    }
}








