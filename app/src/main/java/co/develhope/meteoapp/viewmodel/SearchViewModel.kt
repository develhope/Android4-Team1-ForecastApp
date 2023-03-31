package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<HourlyItem>>>()
    val response: LiveData<ApiResponse<List<HourlyItem>>> = _response

    fun apiCallResultSearch(userSearch: String) {
        _response.postValue(ApiResponse.Loading)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance().serviceGeoCodingApi.getDayEndPointDetails(userSearch).toDomain()
                val itemList = response.map { HourlyItem(city = it, degrees = "", weather = "") }
                _response.postValue(ApiResponse.Success(200, itemList))
            } catch (e: Exception) {
                _response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
            }
        }
    }
}