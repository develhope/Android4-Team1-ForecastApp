package co.develhope.meteoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.Data.DataSearchFrag
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.FragmentSearchBinding
import co.develhope.meteoapp.network.RetrofitInstanceGeocoding
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var newArrayList : List<DataSearchFrag>
    //private lateinit var cityList : Array<String>
    //private lateinit var gradeList : Array<Int>
    //private lateinit var weatherList : Array<String>

    private lateinit var recycleView : RecyclerView

    private var _binding: FragmentSearchBinding? = null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data1()
        val layoutManager = LinearLayoutManager(context)
        recycleView = view.findViewById(R.id.item_list)
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        recycleView.adapter = SearchPlaceAdapter(newArrayList)


        lifecycleScope.launch {
            try {
                RetrofitInstanceGeocoding().getSearchDetails()
            } catch (e: Exception) {
                Log.e("SearchFragment", "Error: ${e.message}")
            }
        }
    }

    private fun data1 (){
        newArrayList = listOf<DataSearchFrag>(
            DataSearchFrag("Milano", 12, "Pioggia"),
            DataSearchFrag("Bergamo", 22 ,"Nuvoloso"),
            DataSearchFrag("Catania", 31 ,"Parzialmente"),
            DataSearchFrag("Siragusa", 17 ,"Soleggiato"))

    }


}

