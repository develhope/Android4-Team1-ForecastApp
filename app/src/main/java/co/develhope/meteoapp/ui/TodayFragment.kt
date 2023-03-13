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
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.network.domainmodel.Weather
import co.develhope.meteoapp.ui.adapter.TodayScreenAdapter
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.ui.adapter.TodayTitle
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


        lifecycleScope.launch {
            try {
               val list1 =  Repository().getDayDetails()
                val adapter = TodayScreenAdapter(emptyList()) // contervire la lista di domain objet in lista di elelemnti ui
                binding.rvTodayScreen.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvTodayScreen.setHasFixedSize(true)
                binding.rvTodayScreen.adapter = adapter
            } catch (e: Exception) {
                Log.e("TodayFragment", "Error: ${e.message}")
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}