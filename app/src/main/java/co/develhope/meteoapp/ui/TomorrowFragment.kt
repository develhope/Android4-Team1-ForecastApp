package co.develhope.meteoapp.ui

import ApiResponse
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentTomorrowBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.mapping.toTomorrowRow
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import co.develhope.meteoapp.ui.adapter.TomorrowAdapter
import co.develhope.meteoapp.ui.adapter.TomorrowSealed
import co.develhope.meteoapp.viewmodel.TomorrowViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

private const val BUNDLE_LIST = "cardOpened"
class TomorrowFragment : Fragment() {

    private var myBinding: FragmentTomorrowBinding? = null
    private val binding get() = myBinding!!
    private val viewModel: TomorrowViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myBinding = FragmentTomorrowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val day = arguments?.getInt("day") ?: 1

        val bottomNavigationView = activity?.findViewById<BottomNavigationView?>(R.id.bottomNavigationView)
        val dayTitle : String = if(day == 1){
            "Domani"
        }else{
            OffsetDateTime.now().plusDays(day.toLong()).format(DateTimeFormatter.ofPattern("EEEE"))
        }

        bottomNavigationView?.menu?.findItem(R.id.domaniFragment)?.title = dayTitle


        if (DataObject.getSelectedCity() == null) {
            this@TomorrowFragment.findNavController()
                .navigate(R.id.cercaFragment)
            Toast.makeText(context, "Per favore seleziona una città per continuare", Toast.LENGTH_SHORT).show()

        } else {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.tomorrowRecyclerView.layoutManager = layoutManager
            binding.tomorrowRecyclerView.setHasFixedSize(true)

            val latitude = DataObject.getSelectedCity()!!.latitude
            val longitude = DataObject.getSelectedCity()!!.longitude
            viewModel.loadData(latitude, longitude)

            viewModel.response.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.Loading -> {
                        binding.loadingView.visibility = View.VISIBLE
                    }
                    is ApiResponse.Success -> {
                        val bundleCard = savedInstanceState?.getString(BUNDLE_LIST)//prendiamo il json con il bundle della lista
                        val itemOpened: MutableList<TomorrowSealed.Row> = try {
                            val listOfMyClassObject = object : TypeToken<MutableList<TomorrowSealed.Row>>() {}.type//gson per convertire una lista ha bisogno di questo costrutto con il toke, è così non ti fare domande
                            Gson().fromJson(bundleCard, listOfMyClassObject)
                        } catch (e: java.lang.Exception) {
                            Log.e("card aperte?", e.toString())
                            mutableListOf() //se non funziona torna lista vuota (quindi di card chiuse)
                        }
                        binding.tomorrowRecyclerView.adapter = TomorrowAdapter(
                            item = response.body.toTomorrowRow(day),
                            day = day,
                            itemOpened = itemOpened
                        )
                        binding.loadingView.visibility = View.GONE
                    }
                    is ApiResponse.Error -> {
                        binding.loadingView.visibility = View.GONE
                        Log.e("TomorrowFragment", "Error")
                        this@TomorrowFragment.findNavController()
                            .navigate(R.id.errorFragment)
                    }
                    else -> {
                        binding.loadingView.visibility = View.GONE
                        Log.e("TomorrowFragment", "Error")
                        this@TomorrowFragment.findNavController()
                            .navigate(R.id.errorFragment)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {     //funzione di sistema che passa un bundle al onviewcreated, aggingiamo a questo bundle la lista di card aperte creata nel adapter
        val dataString = Gson().toJson((binding.tomorrowRecyclerView.adapter as? TomorrowAdapter)?.itemOpened)      //convertiamo la lista in json, as? perché deve essere specificato che sia l'adapter specifico di questa recycler
        outState.putString(BUNDLE_LIST, dataString)          //key word card opened per il riconoscimento, e lo aggiungiamo al bundle
        super.onSaveInstanceState(outState)
    }
}

