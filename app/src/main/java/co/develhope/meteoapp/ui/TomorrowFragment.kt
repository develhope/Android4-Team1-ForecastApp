package co.develhope.meteoapp.ui

import ApiResponse
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
import co.develhope.meteoapp.ui.adapter.TomorrowAdapter
import co.develhope.meteoapp.viewmodel.TomorrowViewModel


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

        var day = arguments?.getInt("day")
        if(day == null){
            day = 1
        }




        if (DataObject.getSelectedCity() == null) {
            this@TomorrowFragment.findNavController()
                .navigate(R.id.cercaFragment)
            Toast.makeText(context, "Seleziona una città per continuare", Toast.LENGTH_SHORT).show()

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
                        binding.tomorrowRecyclerView.adapter = TomorrowAdapter(
                            item = response.body!!.toTomorrowRow(day ?: 0),
                            day = day
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


}

