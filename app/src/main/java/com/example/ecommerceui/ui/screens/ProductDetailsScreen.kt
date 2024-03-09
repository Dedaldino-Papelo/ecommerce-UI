package com.example.ecommerceui.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecommerceui.ui.theme.EcommerceUITheme

@Composable
fun ProductDetailScreen(modifier: Modifier = Modifier){
    Text(
        text = "Details Screen",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    EcommerceUITheme {
        ProductDetailScreen()
    }
}