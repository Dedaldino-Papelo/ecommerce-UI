package com.example.ecommerceui.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecommerceui.R
import com.example.ecommerceui.models.Product
import com.example.ecommerceui.ui.theme.EcommerceUITheme

@Composable
fun ProductDetailScreen(product: Product, modifier: Modifier = Modifier){
    Text(
        text = stringResource(product.productName),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    EcommerceUITheme {
        ProductDetailScreen(
            product = Product(
                "1",
                R.drawable.ellipse2,
                R.string.product_name_1,
                7.2))
    }
}