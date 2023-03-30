package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.network.domainmodel.TomorrowRow
import kotlinx.coroutines.launch


class TomorrowViewModel : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<TomorrowRow>>>()
    val response: LiveData<ApiResponse<List<TomorrowRow>>> = _response


    fun loadData(latitude: Double, longitude: Double) {
        _response.postValue(ApiResponse.Loading)
        viewModelScope.launch {
            try {
                val retrofitService = RetrofitInstance().serviceMeteoApi
                val hourlyData = retrofitService.getDayEndPointDetails(
                    latitude = latitude,
                    longitude = longitude
                ).toDomain()
                _response.postValue(ApiResponse.Success(200, hourlyData))
            } catch (e: Exception) {
                _response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
            }
        }
    }




}

