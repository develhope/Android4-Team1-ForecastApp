package co.develhope.meteoapp.ui

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
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentTomorrowBinding
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import co.develhope.meteoapp.network.mapping.toHomeCards
import co.develhope.meteoapp.network.mapping.toTomorrowRow
import co.develhope.meteoapp.ui.adapter.TomorrowAdapter
import co.develhope.meteoapp.ui.adapter.TomorrowSealed
import co.develhope.meteoapp.ui.adapter.TomorrowTitle
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime


class TomorrowFragment : Fragment() {

    private var myBinding: FragmentTomorrowBinding? = null
    private val binding get() = myBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myBinding = FragmentTomorrowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.tomorrowRecyclerView.layoutManager = layoutManager
        binding.tomorrowRecyclerView.setHasFixedSize(true)


        lifecycleScope.launch {
            try {
                //val response = RetrofitInstanceApiOpenMeteo.getWeeklyDetails().toDomain()
                val response =
                    RetrofitInstance().serviceMeteoApi.getDayEndPointDetails(start_date = "2023-03-16", end_date = "2023-03-17").toDomain()

                binding.tomorrowRecyclerView.adapter = TomorrowAdapter(
                    item = response.toTomorrowRow()
                    )
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error: ${e.message}")
            }
        }

    }


    }

