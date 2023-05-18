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
import co.develhope.meteoapp.ui.adapter.TodayScreenData
import co.develhope.meteoapp.viewmodel.TodayViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.inject

private const val BUNDLE_LIST = "cardOpened"

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
                        val bundleCard =
                        savedInstanceState?.getString(BUNDLE_LIST)//prendiamo il json con il bundle della lista
                        val itemOpened: MutableList<TodayScreenData.ForecastData> = try {
                            val listOfMyClassObject = object :
                                TypeToken<MutableList<TodayScreenData.ForecastData>>() {}.type//gson per convertire una lista ha bisogno di questo costrutto con il toke, è così non ti fare domande
                            Gson().fromJson(bundleCard, listOfMyClassObject)
                        } catch (e: java.lang.Exception) {
                            Log.e("card aperte?", e.toString())
                            mutableListOf() //se non funziona torna lista vuota (quindi di card chiuse)
                        }
                        binding.rvTodayScreen.adapter = TodayScreenAdapter(
                            items = response.body!!.toTodayCardInfo(
                                viewModelToday.getSelectedCityName(),
                                viewModelToday.getSelectedCityRegion(),
                            ),
                            itemOpened = itemOpened
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

    override fun onSaveInstanceState(outState: Bundle) {     //funzione di sistema che passa un bundle al onviewcreated, aggingiamo a questo bundle la lista di card aperte creata nel adapter
        val dataString =
            Gson().toJson((binding.rvTodayScreen.adapter as? TodayScreenAdapter)?.itemOpened)      //convertiamo la lista in json, as? perché deve essere specificato che sia l'adapter specifico di questa recycler
        outState.putString(
            BUNDLE_LIST,
            dataString
        )          //key word card opened per il riconoscimento, e lo aggiungiamo al bundle
        super.onSaveInstanceState(outState)
    }
}


