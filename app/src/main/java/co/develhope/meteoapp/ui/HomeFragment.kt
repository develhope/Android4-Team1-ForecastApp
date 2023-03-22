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
import co.develhope.meteoapp.databinding.FragmentHomeBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.mapping.toHomeCards
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var baseContainerBinding: FragmentHomeBinding? = null
    private val binding get() = baseContainerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseContainerBinding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val window = activity?.window
        if (window != null) {
            window.statusBarColor = context?.getColor(R.color.home_background) ?: 0
        }*/

        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        lifecycleScope.launch {
            try {
                binding.loadingView.visibility = View.VISIBLE
                //val response = RetrofitInstanceApiOpenMeteo.getWeeklyDetails().toDomain()
                val response =
                    RetrofitInstance().serviceMeteoApi.getWeeklyEndPointDetails(
                        DataObject.getSelectedCity()!!.latitude,//if != null fai la chiamata, altrimenti verso search
                        DataObject.getSelectedCity()!!.longitude
                    ).toDomain()

                binding.recyclerView.adapter = HomeAdapter(
                    list = response.toHomeCards(),
                    onClick = {
                        when (it) {
                            HomeScreenEvents.Today -> this@HomeFragment.findNavController()
                                .navigate(R.id.oggiFragment)
                            HomeScreenEvents.Tomorrow -> this@HomeFragment.findNavController()
                                .navigate(R.id.domaniFragment)
                            HomeScreenEvents.OtherDay() -> Toast.makeText(
                                context,
                                "da implementare",
                                Toast.LENGTH_SHORT
                            ).show()
                            else -> {
                                this@HomeFragment.findNavController()
                                    .navigate(R.id.errorFragment)
                                /*Toast.makeText(
                                    context,
                                    "error",
                                    Toast.LENGTH_SHORT
                                ).show()*/
                            }
                        }
                    })
                binding.loadingView.visibility = View.GONE
            } catch (e: Exception) {
                binding.loadingView.visibility = View.GONE
                Log.e("HomeFragment", "Error: ${e.message}")
                this@HomeFragment.findNavController()
                    .navigate(R.id.errorFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseContainerBinding = null
    }

}