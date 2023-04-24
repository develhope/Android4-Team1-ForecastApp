package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.sharedpreferences.MySharedPrefsInterface
import co.develhope.meteoapp.sharedpreferences.SharedImplementation
import co.develhope.meteoapp.ui.SearchScreen.HourlyItem
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SearchViewModel(val sharedImplementation: MySharedPrefsInterface, val retrofitInstance: RetrofitInstance) : ViewModel() {

     val response = MutableLiveData<ApiResponse<List<HourlyItem>>>()
    fun getSearchCity() = sharedImplementation.getSearchCity()
    fun setSelectedCity(place : HourlyItem) = sharedImplementation.setSelectedCity(place)

    fun apiCallResultSearch(userSearch: String) {
        response.postValue(ApiResponse.Loading)
        viewModelScope.launch {
            try {
                val response = retrofitInstance.serviceGeoCodingApi.getDayEndPointDetails(userSearch).toDomain()
                val itemList = response.map { HourlyItem(city = it, degrees = "", weather = "") }
                this@SearchViewModel.response.postValue(ApiResponse.Success(200, itemList))
            } catch (e: Exception) {
                response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
            }
        }
    }
}