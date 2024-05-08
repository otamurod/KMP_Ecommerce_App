package uz.otamurod.kmp.ecommerceApp.api

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import uz.otamurod.kmp.ecommerceApp.api.model.Products
import uz.otamurod.kmp.ecommerceApp.util.Constants.BASE_URL
import uz.otamurod.kmp.ecommerceApp.util.RequestState

class ProductsApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    fun fetchProducts(limit: Int): Flow<RequestState> {
        return flow {
            emit(RequestState.Loading)
            delay(2000)
            try {
                emit(
                    RequestState.Success(
                        data = Products(
                            items = httpClient.get(urlString = "${BASE_URL}products?limit=$limit").body()
                        )
                    )
                )
            } catch (e: Exception) {
                Logger.setTag("ProductsApi")
                Logger.e { e.message.toString() }
                emit(RequestState.Error(message = "Error while fetching the data."))
            }
        }
    }
}