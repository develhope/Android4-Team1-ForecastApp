package co.develhope.meteoapp.ui

import ApiResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentTodayBinding
import co.develhope.meteoapp.network.mapping.toTodayCardInfo
import co.develhope.meteoapp.ui.adapter.TodayScreenAdapter
import co.develhope.meteoapp.viewmodel.TodayViewModel
import org.koin.android.ext.android.inject


class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private val viewModelToday: TodayViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModelToday.isSelectedCityNull()) {
            this@TodayFragment.findNavController().navigate(R.id.cercaFragment)
            Toast.makeText(context, "Seleziona una città per continuare", Toast.LENGTH_SHORT).show()
        } else {

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvTodayScreen.layoutManager = layoutManager
            binding.rvTodayScreen.setHasFixedSize(true)

            viewModelToday.apiCallResultToday()

            viewModelToday.response.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.Loading -> {
                        binding.loadingView.visibility = View.VISIBLE
                    }
                    is ApiResponse.Success -> {
                        binding.rvTodayScreen.adapter = TodayScreenAdapter(
                            items = response.body!!.toTodayCardInfo(
                                viewModelToday.getSelectedCityName(),
                                viewModelToday.getSelectedCityRegion()
                            )
                        )
                        binding.loadingView.visibility = View.GONE
                    }
                    else -> {
                        binding.loadingView.visibility = View.GONE
                        Log.e("HomeFragment", "Error")
                        ErrorFragment(onOkClickListener = {
                            if (viewModelToday.isSelectedCityNull()) {
                                this@TodayFragment.findNavController()
                                    .navigate(R.id.cercaFragment)
                                Toast.makeText(
                                    context,
                                    "Seleziona una città per continuare",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {
                                viewModelToday.apiCallResultToday()
                            }

                        }).show(childFragmentManager, ErrorFragment.TAG)
                    }
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}