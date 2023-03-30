package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import kotlinx.coroutines.launch

class TodayViewModel : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<TodayCardInfo>>>()
    val response: LiveData<ApiResponse<List<TodayCardInfo>>> = _response

    fun apiCallResultToday(latitude: Double, longitude: Double) {
        _response.postValue(ApiResponse.Loading)
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance().serviceMeteoApi
                val hourlyData = retrofit.getDayEndPointDetails(
                    latitude = latitude,
                    longitude = longitude
                ).toDomainToday()
                _response.postValue(ApiResponse.Success(200, hourlyData))
            } catch (e: Exception) {
                _response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
            }
        }
    }
}