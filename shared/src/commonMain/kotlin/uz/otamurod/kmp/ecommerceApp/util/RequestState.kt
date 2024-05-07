package uz.otamurod.kmp.ecommerceApp.util

import kotlinx.serialization.Serializable
import uz.otamurod.kmp.ecommerceApp.api.model.Products

@Serializable
sealed class RequestState {
    @Serializable
    data object Idle : RequestState()

    @Serializable
    data object Loading : RequestState()

    @Serializable
    data class Success(val data: Products) : RequestState()

    @Serializable
    data class Error(val message: String) : RequestState()

    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getProducts(): Products = (this as Success).data

    fun getErrorMessage(): String = (this as Error).message
}
