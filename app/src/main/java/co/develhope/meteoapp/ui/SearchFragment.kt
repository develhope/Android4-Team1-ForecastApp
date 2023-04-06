package co.develhope.meteoapp.ui

import ApiResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentSearchBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.DataObject.getSearchCity
import co.develhope.meteoapp.ui.adapter.SearchPlaceAdapter
import co.develhope.meteoapp.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModelSearch: SearchViewModel by viewModels()
    private val searchAdapter = SearchPlaceAdapter(getSearchCity()) {
        if (it != null) {
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

                viewModelSearch.apiCallResultSearch(newText.orEmpty())
                manageView()
                return true
            }

        })
        viewModelSearch.response.observe(viewLifecycleOwner) { responseSearch ->
            when (responseSearch) {
                is ApiResponse.Loading -> {
                }
                is ApiResponse.Success -> {
                    searchAdapter.setNewList(responseSearch.body!!)
                }
                is ApiResponse.Error -> {
                    Log.e("SearchFragment", "Error: ${responseSearch.message}")
                    this@SearchFragment.findNavController().navigate(R.id.errorFragment)
                }
                else -> {
                    //binding.loadingView.visibility = View.GONE
                    Log.e("SearchFragment", "Error: ${500}")
                    this@SearchFragment.findNavController().navigate(R.id.errorFragment)
                }
            }
        }

    }

    fun manageView() {
        binding.ricercheRecenti.isVisible = binding.frame48.query.isEmpty()
    }
}











