package uz.otamurod.kmp.ecommerceApp.android.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.otamurod.kmp.ecommerceApp.api.ProductsApi
import uz.otamurod.kmp.ecommerceApp.util.RequestState

class HomeViewModel : ViewModel() {
    private var _requestState: MutableState<RequestState> = mutableStateOf(RequestState.Idle)
    val requestState: State<RequestState> = _requestState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            ProductsApi().fetchProducts(limit = 10).collectLatest {
                withContext(Dispatchers.Main) {
                    _requestState.value = it
                }
            }
        }
    }
}