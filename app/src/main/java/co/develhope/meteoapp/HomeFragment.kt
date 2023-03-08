package co.develhope.meteoapp


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
import co.develhope.meteoapp.Home.HomeScreenEvents
import co.develhope.meteoapp.databinding.FragmentHomeBinding
import co.develhope.meteoapp.network.RetrofitInstanceApiOpenMeteo
import co.develhope.meteoapp.network.mapping.toHomeCards
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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        lifecycleScope.launch {
            try {

                binding.recyclerView.adapter = HomeAdapter(
                    RetrofitInstanceApiOpenMeteo.getWeeklyDetails().toHomeCards(),
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
                                Toast.makeText(
                                    context,
                                    "error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error: ${e.message}")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseContainerBinding = null
    }

}


