package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.Place
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<Place>>>()
    val response: LiveData<ApiResponse<List<Place>>> = _response

    fun apiCallResultToday(userSearch : String) {
        _response.postValue(ApiResponse.Loading)
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance().serviceGeoCodingApi
                val hourlyData = retrofit.getDayEndPointDetails(
                    name = userSearch
                ).toDomain()
                _response.postValue(ApiResponse.Success(200, hourlyData))
            } catch (e: Exception) {
                _response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
            }
        }
    }
}