package co.develhope.meteoapp.viewmodel

import ApiResponse
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.sharedpreferences.SharedImplementation
import kotlinx.coroutines.launch

class TodayViewModel : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<TodayCardInfo>>>()
    val response: LiveData<ApiResponse<List<TodayCardInfo>>> = _response

    val sharedImplementation : SharedImplementation? = null  //quando ci saranno le dipendency questo sarà nel construttore del viewmodel

    fun isSelectedCityNull() = sharedImplementation?.getSelectedCity() == null
    fun getSelectedCityLatitude() = sharedImplementation?.getSelectedCity()?.latitude
    fun getSelectedCityLongitude() = sharedImplementation?.getSelectedCity()?.longitude
    fun getSelectedCityName() = sharedImplementation?.getSelectedCity()?.name.orEmpty()
    fun getSelectedCityRegion() = sharedImplementation?.getSelectedCity()?.region.orEmpty()



    fun apiCallResultToday() {
        _response.postValue(ApiResponse.Loading)
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance().serviceMeteoApi
                val hourlyData = retrofit.getDayEndPointDetails(
                    latitude = getSelectedCityLatitude()!!,
                    longitude = getSelectedCityLongitude()!!
                ).toDomainToday()
                _response.postValue(ApiResponse.Success(200, hourlyData))
            } catch (e: Exception) {
                _response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
            }
        }
    }
}