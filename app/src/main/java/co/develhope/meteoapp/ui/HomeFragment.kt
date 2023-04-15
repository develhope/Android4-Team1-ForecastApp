package co.develhope.meteoapp.ui


import ApiResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentHomeBinding
import co.develhope.meteoapp.network.DataObject
import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.network.dto.WeeklyData
import co.develhope.meteoapp.network.mapping.toHomeCards
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenElements
import co.develhope.meteoapp.ui.adapter.home_adapter.HomeScreenEvents
import co.develhope.meteoapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var baseContainerBinding: FragmentHomeBinding? = null
    private val binding get() = baseContainerBinding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseContainerBinding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataObject.setSharedPrefe( //centralizziamo la creazione dentro application o DI
            activity?.getSharedPreferences(
                "Place", //mettiamo tutto nel viewmodel o repo
                AppCompatActivity.MODE_PRIVATE
            )
        )

        if (DataObject.getSelectedCity() == null) {
            this@HomeFragment.findNavController()
                .navigate(R.id.cercaFragment)
            Toast.makeText(context, "Seleziona una città per continuare", Toast.LENGTH_SHORT).show()
        } else {

            binding.recyclerView.layoutManager = LinearLayoutManager(context)

            val latitude = DataObject.getSelectedCity()!!.latitude
            val longitude = DataObject.getSelectedCity()!!.longitude
            viewModel.loadData(latitude, longitude)


            viewModel.response.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ApiResponse.Loading -> {
                        binding.loadingView.visibility = View.VISIBLE
                    }
                    is ApiResponse.Success -> {
                        setUpAdapter(response.body.toHomeCards())
                        binding.loadingView.visibility = View.GONE
                    }
                    else -> {
                        binding.loadingView.visibility = View.GONE
                        Log.e("HomeFragment", "Error")
                            ErrorFragment(requireContext(), onOkClickListener = {
                                if (DataObject.getSelectedCity() == null) {
                                    this@HomeFragment.findNavController()
                                        .navigate(R.id.cercaFragment)
                                    Toast.makeText(context, "Seleziona una città per continuare", Toast.LENGTH_SHORT).show()

                                } else {
                                    viewModel.loadData(latitude, longitude)
                                }

                            }).show(childFragmentManager, ErrorFragment.TAG)
                    }

                }
            }
        }


    }


    fun setUpAdapter(list: List<HomeScreenElements>) {
        binding.recyclerView.adapter = HomeAdapter(
            list = list,
            onClick = { homeScreenEvent ->
                when (homeScreenEvent) {
                    HomeScreenEvents.Today -> this@HomeFragment.findNavController()
                        .navigate(R.id.oggiFragment)
                    is HomeScreenEvents.Tomorrow -> this@HomeFragment.findNavController()
                        .navigate(R.id.domaniFragment)
                    is HomeScreenEvents.OtherDay -> this@HomeFragment.findNavController()
                        .navigate(R.id.domaniFragment, bundleOf("day" to homeScreenEvent.day))
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseContainerBinding = null
    }

}
