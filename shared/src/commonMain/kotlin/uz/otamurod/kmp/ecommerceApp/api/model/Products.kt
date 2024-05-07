package uz.otamurod.kmp.ecommerceApp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Products(
    val items: List<ProductResponse>
)
