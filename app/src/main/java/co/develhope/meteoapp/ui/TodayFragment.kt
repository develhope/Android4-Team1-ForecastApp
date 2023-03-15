package co.develhope.meteoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentTodayBinding
import co.develhope.meteoapp.network.Repository
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.network.mapping.toTodayCardInfo
import co.develhope.meteoapp.network.mapping.toTomorrowRow
import co.develhope.meteoapp.ui.adapter.TodayScreenAdapter
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.ui.adapter.TodayTitle
import co.develhope.meteoapp.ui.adapter.TomorrowAdapter
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime


class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTodayScreen.layoutManager = layoutManager
        binding.rvTodayScreen.setHasFixedSize(true)

        lifecycleScope.launch {
            try {
                //val response = RetrofitInstanceApiOpenMeteo.getWeeklyDetails().toDomain()
                val response =
                    RetrofitInstance().serviceMeteoApi.getDayEndPointDetails().toDomainToday()

                binding.rvTodayScreen.adapter = TodayScreenAdapter(
                    items = response.toTodayCardInfo()
                )
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error: ${e.message}")
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}