package uz.otamurod.kmp.ecommerceApp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform