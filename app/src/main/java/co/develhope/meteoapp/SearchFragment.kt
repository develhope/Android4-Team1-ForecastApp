package co.develhope.meteoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.data.DataSearchFrag
import co.develhope.meteoapp.data.UserAdapter
import co.develhope.meteoapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var newArrayList : ArrayList<DataSearchFrag>
    private lateinit var cityList : Array<String>
    private lateinit var gradeList : Array<Int>
    private lateinit var weatherList : Array<String>

    private lateinit var recycleView : RecyclerView

    private var _binding: FragmentSearchBinding? = null
    private val binding get()=_binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data1()
        val layoutManager = LinearLayoutManager(context)
        recycleView = view.findViewById(R.id.item_list)
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        recycleView.adapter = UserAdapter(newArrayList)
    }

    private fun data1 (){
        newArrayList = arrayListOf<DataSearchFrag>()

        cityList = arrayOf("Milano", "Bergamo", "Catania", "Siracusa")
        gradeList = arrayOf(12, 22, 31, 17)
        weatherList = arrayOf("pioggioloso", "bruttobello", "ciao", "fa caldo")

        for(i in cityList.indices){
            val item = DataSearchFrag(cityList[i], gradeList[i], weatherList[i])
            newArrayList.add(item)
        }
    }

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
}

