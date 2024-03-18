package com.example.ecommerceui.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ecommerceui.R
import com.example.ecommerceui.models.Product
import com.example.ecommerceui.ui.theme.EcommerceUITheme

@Composable
fun ProductDetailScreen(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.default_color))
    ) {
        Image(
            painter = painterResource(product.productImageRes) ,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(272.dp)
                .offset(y = 150.dp)
                .zIndex(1f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(colorResource(R.color.white)),
        ) {
                Text(
                    text = stringResource(product.productDesc),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(top = 150.dp)
                )

                ProductDetail(productName = product.productName)
                ProductLocalization()
                PriceAndButton()
        }
    }
}

@Composable
fun ProductLocalization(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Top
        ) {
            Icon(painter = painterResource(R.drawable.hebei_fc),
                contentDescription = null,
                modifier = Modifier
                    .width(35.dp)
                    .height(39.dp)
            )
            Column {
                Text(text = "Chin Club", fontSize = 14.sp)
                Text(
                    text = "3.1 km from you",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(R.color.grey_color)
                )
            }
        }

        Icon(
            Icons.Filled.Star,
            tint = colorResource(R.color.star_color),
            contentDescription = null
        )
    }
}
@Composable
fun ProductDetail(productName: Int, modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 19.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ){
        Column {
            Text(
                text = stringResource(productName),
                style = MaterialTheme.typography.displayMedium,
                fontSize = 24.sp
            )
            Text(text = "300g/530 kcal")
        }
        Text(text = "1 portion")
    }
}

@Composable
fun PriceAndButton(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Price")
            Text(text = "$7,50")
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.default_color)
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.btn_add_cart),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = modifier.width(15.dp))
                Icon(
                    Icons.Filled.AddCircle,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    }
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
                R.string.product_desc1,
                7.2))
    }
}