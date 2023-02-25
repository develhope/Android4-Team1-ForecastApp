package co.develhope.meteoapp.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.Weather
import co.develhope.meteoapp.databinding.FragmentTodayBinding
import org.threeten.bp.OffsetDateTime


class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val listItems = mutableListOf(
            TodayCardInfo("11:00",Weather.SUNNY,31,0,45,5/10,60,7,24,0),
            TodayCardInfo("12:00",Weather.SUNNY,29,0,45,5/10,60,7,24,0),
            TodayCardInfo("13:00",Weather.SUNNY,30,0,45,5/10,60,7,24,0),
            TodayCardInfo("14:00",Weather.RAINY,32,0,45,5/10,60,7,24,0),
            TodayCardInfo("15:00",Weather.RAINY,28,0,45,5/10,60,7,24,0),
            TodayCardInfo("16:00",Weather.CLOUDY,25,0,45,5/10,60,7,24,0),
            TodayCardInfo("16:00",Weather.SUNNY,22,0,45,5/10,60,7,24,0),
        )*/

        val title = TodayTitle(
            "Palermo", "Sicilia",
            OffsetDateTime.now()
        )
        val card1 =
            TodayCardInfo(OffsetDateTime.now(), Weather.SUNNY, 31, 0, 45, 5 / 10, 60, 7, 24, 0)
        val card2 = TodayCardInfo(
            OffsetDateTime.now().plusHours(1),
            Weather.RAINY,
            31,
            0,
            38,
            8 / 10,
            70,
            9,
            30,
            0
        )
        val card3 = TodayCardInfo(
            OffsetDateTime.now().plusHours(2),
            Weather.CLOUDY,
            31,
            0,
            41,
            6 / 10,
            65,
            12,
            24,
            0
        )
        val list1 = listOf<TodayScreenData>(
            TodayScreenData.TodayTitleObject(title),
            TodayScreenData.ForecastData(card1),
            TodayScreenData.ForecastData(card2),
            TodayScreenData.ForecastData(card3)
        )

        val adapter = TodayScreenAdapter(list1)
        binding.rvTodayScreen.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTodayScreen.setHasFixedSize(true)
        binding.rvTodayScreen.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}