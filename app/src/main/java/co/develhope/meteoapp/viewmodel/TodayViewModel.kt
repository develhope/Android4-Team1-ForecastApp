package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.TodayCardInfo
import co.develhope.meteoapp.sharedpreferences.MySharedPrefsInterface
import kotlinx.coroutines.launch

class TodayViewModel(
    val sharedImplementation: MySharedPrefsInterface,
    val retrofitInstance: RetrofitInstance
) : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<TodayCardInfo>>>()
    val response: LiveData<ApiResponse<List<TodayCardInfo>>> = _response

    private var latitude: Double? = null
    private var longitude: Double? = null


    fun isSelectedCityNull() = sharedImplementation.getSelectedCity() == null
    fun getSelectedCityLatitude() = sharedImplementation.getSelectedCity()?.latitude
    fun getSelectedCityLongitude() = sharedImplementation.getSelectedCity()?.longitude
    fun getSelectedCityName() = sharedImplementation.getSelectedCity()?.name.orEmpty()
    fun getSelectedCityRegion() = sharedImplementation.getSelectedCity()?.region.orEmpty()


    fun apiCallResultToday() {
        if (_response.value == null ||
            this.latitude != latitude ||
            this.longitude != longitude ||
            _response.value is ApiResponse.Error ||
            latitude != null ||
            longitude != null
        ) {
            _response.postValue(ApiResponse.Loading)
            viewModelScope.launch {
                try {
                    val retrofit = retrofitInstance.serviceMeteoApi
                    val hourlyData = retrofit.getDayEndPointDetails(
                        latitude = getSelectedCityLatitude()!!,
                        longitude = getSelectedCityLongitude()!!
                    )
                    if (hourlyData.isSuccessful) {
                        _response.postValue(
                            ApiResponse.Success(
                                hourlyData.code(),
                                hourlyData.body()?.toDomainToday()
                            )
                        )
                    } else {
                        _response.postValue(
                            ApiResponse.Error(
                                hourlyData.code(),
                                hourlyData.errorBody()?.toString() ?: "Error"
                            )
                        )
                    }
                } catch (e: Exception) {
                    _response.postValue(ApiResponse.Error(500, e.message ?: "Error"))
                }
            }
        }
    }
}