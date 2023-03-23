package co.develhope.meteoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentTomorrowBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.mapping.toTomorrowRow
import co.develhope.meteoapp.ui.adapter.TomorrowAdapter
import kotlinx.coroutines.launch


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
                binding.loadingView.visibility = View.VISIBLE
                //val response = RetrofitInstanceApiOpenMeteo.getWeeklyDetails().toDomain()
                val response =
                    if (DataObject.getSelectedCity()?.latitude != null && DataObject.getSelectedCity()?.longitude != null) {
                        RetrofitInstance().serviceMeteoApi.getDayEndPointDetails(
                            DataObject.getSelectedCity()!!.latitude,
                            DataObject.getSelectedCity()!!.longitude
                        ).toDomain()
                    } else {
                        null
                    }
                binding.tomorrowRecyclerView.adapter = TomorrowAdapter(
                    item = response.toTomorrowRow()
                )
                binding.loadingView.visibility = View.GONE
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error: ${e.message}")
                this@TomorrowFragment.findNavController()
                    .navigate(R.id.errorFragment)
            }
        }

    }


}

