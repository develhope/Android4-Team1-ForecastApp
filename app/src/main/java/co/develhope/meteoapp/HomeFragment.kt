package co.develhope.meteoapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Data.Data.getListAdapter
import co.develhope.meteoapp.databinding.FragmentHomeBinding

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
        binding.recyclerView.adapter = HomeAdapter(getListAdapter())

    }
    override fun onDestroyView() {
        super.onDestroyView()
        baseContainerBinding = null
    }

}


