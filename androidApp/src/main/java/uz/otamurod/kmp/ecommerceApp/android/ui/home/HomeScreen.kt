package uz.otamurod.kmp.ecommerceApp.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.otamurod.kmp.ecommerceApp.android.ui.components.ProductCard

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val requestState by viewModel.requestState

    if (requestState.isLoading()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else if (requestState.isSuccess()) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = requestState.getProducts().items,
                    key = { it.id }
                ) { item ->
                    ProductCard(product = item)
                }
            }
        }
    } else if (requestState.isError()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(all = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = requestState.getErrorMessage(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}