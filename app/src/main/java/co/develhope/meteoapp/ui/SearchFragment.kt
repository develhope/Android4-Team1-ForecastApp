package co.develhope.meteoapp.ui

import ApiResponse
import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentSearchBinding
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import co.develhope.meteoapp.ui.adapter.SearchPlaceAdapter
import co.develhope.meteoapp.viewmodel.SearchViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import org.koin.android.ext.android.inject


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModelSearch: SearchViewModel by inject()
    private var searchAdapter: SearchPlaceAdapter? = null
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLocationGeo()
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

        if (searchAdapter == null) {
            searchAdapter =
                SearchPlaceAdapter(viewModelSearch.getSearchCity()) {//se torniamo qua da un'altra schermata non lo riassegna perché non è null e non dobbiamo ricreare
                    if (it != null) {
                        viewModelSearch.setSelectedCity(it)
                        findNavController().navigate(R.id.homeFragment)
                    }
                }
        }

        val layoutManager = LinearLayoutManager(context)
        binding.itemList.layoutManager = layoutManager
        binding.itemList.adapter = searchAdapter

        binding.frame48.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //do nothings
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    viewModelSearch.apiCallResultSearch(newText)
                }

                manageView()
                return true
            }


        })

        viewModelSearch.response.observe(viewLifecycleOwner) { responseSearch ->
            when (responseSearch) {
                is ApiResponse.Loading -> {
                }
                is ApiResponse.Success -> {
                    searchAdapter?.setNewList(responseSearch.body!!)
                }
                is ApiResponse.Error -> {
                    Log.e("SearchFragment", "Error: ${responseSearch.message}")
                    ErrorFragment(onOkClickListener = {
                        viewModelSearch.apiCallResultSearch(binding.frame48.query.toString())
                    }).show(childFragmentManager, ErrorFragment.TAG)
                }
                else -> {
                    searchAdapter?.setNewList(viewModelSearch.getSearchCity())
                }
            }
        }

        binding.locationIcon.setOnClickListener {
            getLocationGeo()
        }
    }

    fun getLocationGeo() {

        val locationManager =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        } else {
            locationManager.getCurrentLocation(
                com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY,
                CancellationTokenSource().token,
            )
                .addOnSuccessListener { location ->
                    val geoResult = Geocoder(requireContext()).getFromLocation(
                        location.latitude,
                        location.longitude,
                        1
                    )
                    val placeGeo = Place(
                        name = geoResult?.firstOrNull()?.locality.orEmpty(),
                        latitude = location.latitude,
                        longitude = location.longitude,
                        region = geoResult?.firstOrNull()?.countryName.orEmpty()
                    )
                    viewModelSearch.setSelectedCity(HourlyItem("","", placeGeo))
                    findNavController().navigate(R.id.homeFragment)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        "cerca una città manualmente",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }


    fun manageView() {
        if (binding.frame48.query.isEmpty()) {
            binding.ricercheRecenti.visibility = VISIBLE

            viewModelSearch.response.postValue(null)
        } else {
            binding.ricercheRecenti.visibility = GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onPause() {
        super.onPause()
        binding.frame48.setQuery(null, false)
        binding.frame48.isIconified = true
    }
}











