sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<T>(val code: Int, val body: T?) : ApiResult<T>()
    data class Error(val code: Int, val message: String) : ApiResult<Nothing>()
}