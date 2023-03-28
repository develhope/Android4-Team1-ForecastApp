package co.develhope.meteoapp.viewmodel

import ApiResult
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.network.ForecastApiService
import co.develhope.meteoapp.network.dto.WeeklyData
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeViewModel : ViewModel() {

    private var _weather =
        MutableLiveData<ApiResult<WeeklyData>>()
    val weather: LiveData<ApiResult<WeeklyData>>
        get() = _weather

    private var forecastApiService: ForecastApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastApiService = retrofit.create(ForecastApiService::class.java)
    }

    fun getWeeklyForecast(latitude: Double, longitude: Double) {
        _weather.postValue(ApiResult.Loading)
        viewModelScope.launch {
            try {
                val response = forecastApiService.getWeeklyEndPointDetails(latitude, longitude)
                if (response.isSuccessful) {
                    _weather.postValue(ApiResult.Success(response.code(), response.body()))
                } else {
                    _weather.postValue(ApiResult.Error(response.code(), response.message()))
                }
            } catch (e: Exception) {
                _weather.postValue(ApiResult.Error(500, "ci sono problemi"))
                Log.e("HomeViewModel", "Error: ${e.message}")
            }
        }
    }}