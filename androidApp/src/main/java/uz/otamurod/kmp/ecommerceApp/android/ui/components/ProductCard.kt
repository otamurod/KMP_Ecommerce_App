package uz.otamurod.kmp.ecommerceApp.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.otamurod.kmp.ecommerceApp.api.model.ProductResponse

@Composable
fun ProductCard(
    product: ProductResponse
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Column {
            Surface(
                modifier = Modifier.padding(all = 12.dp),
                shape = RoundedCornerShape(size = 12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(all = 10.dp)) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = product.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = product.description,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SuggestionChip(
                        shape = CircleShape,
                        onClick = {
                            /* Do something! */
                        },
                        label = {
                            Text(text = product.category)
                        }
                    )
                    Text(
                        text = "$${product.price}",
                        fontWeight = FontWeight.Medium,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }
        }
    }
}

@Preview(name = "ProductCard")
@Composable
private fun PreviewProductCard() {
    ProductCard(
        product = ProductResponse(
            id = 1,
            title = "There will be title",
            price = 100.0,
            description = "There will be the product description",
            category = "Men",
            image = ""
        )
    )
}