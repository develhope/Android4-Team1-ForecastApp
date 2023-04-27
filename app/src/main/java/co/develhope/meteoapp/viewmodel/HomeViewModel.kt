package co.develhope.meteoapp.viewmodel

import ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.network.domainmodel.HomeCards
import co.develhope.meteoapp.sharedpreferences.MySharedPrefsInterface
import kotlinx.coroutines.launch


class HomeViewModel(
    private val sharedImplementation: MySharedPrefsInterface,
    private val retrofitInstance: RetrofitInstance
) : ViewModel() {

    private val _response = MutableLiveData<ApiResponse<List<HomeCards>?>>()
    val response: LiveData<ApiResponse<List<HomeCards>?>> = _response

    private var latitude: Double? = null
    private var longitude: Double? = null

    fun isSelectedCityNull() = sharedImplementation.getSelectedCity() == null
    private fun getSelectedCityLatitude() = sharedImplementation.getSelectedCity()?.latitude
    private fun getSelectedCityLongitude() = sharedImplementation.getSelectedCity()?.longitude
    fun getSelectedCityName() = sharedImplementation.getSelectedCity()?.name.orEmpty()
    fun getSelectedCityRegion() = sharedImplementation.getSelectedCity()?.region.orEmpty()

    fun loadData(
        latitude: Double? = getSelectedCityLatitude(),
        longitude: Double? = getSelectedCityLongitude()
    ) {
        if (_response.value == null ||
            this.latitude != latitude ||
            this.longitude != longitude ||
            _response.value is ApiResponse.Error ||
            latitude != null ||
            longitude != null
        ) {

            this.latitude = latitude
            this.longitude = longitude
            _response.postValue(ApiResponse.Loading)

            viewModelScope.launch {
                try {
                    val retrofitService = retrofitInstance.serviceMeteoApi
                    val weeklyData = retrofitService.getWeeklyEndPointDetails(
                        latitude = latitude!!,
                        longitude = longitude!!
                    )

                    if (weeklyData.isSuccessful) {
                        _response.postValue(
                            ApiResponse.Success(
                                weeklyData.code(),
                                weeklyData.body()?.toDomain()
                            )
                        )
                    } else {
                        _response.postValue(
                            ApiResponse.Error(
                                weeklyData.code(),
                                weeklyData.errorBody()?.toString() ?: "Error"
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