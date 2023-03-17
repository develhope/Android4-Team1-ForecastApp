package co.develhope.meteoapp.network

sealed class Response<out T>{
    object Loading : Response<Nothing>()
    data class Success<T>(val code : Int, val body : T?) : Response<T>()
    data class Error(val code : Int, val message: String) : Response<Nothing>()
}