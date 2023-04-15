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

    private var latitude : Double? = null
    private var longitude : Double? = null


    fun loadData(latitude: Double, longitude: Double) {
        if (_response.value == null || this.latitude != latitude || this.longitude != longitude || _response.value is ApiResponse.Error) {

            this.latitude = latitude
            this.longitude = longitude
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



}


