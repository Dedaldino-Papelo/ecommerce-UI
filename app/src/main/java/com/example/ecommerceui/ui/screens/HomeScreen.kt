package com.example.ecommerceui.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceui.R
import com.example.ecommerceui.data.DataSource
import com.example.ecommerceui.models.Category
import com.example.ecommerceui.models.Product
import com.example.ecommerceui.ui.theme.EcommerceUITheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column() {
            Text(text = "Hi James",  style = MaterialTheme.typography.titleLarge)
            Text(text = "What do you want to order today?",  style = MaterialTheme.typography.bodyLarge)
        }
        EditText(
            modifier = Modifier
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            items(DataSource.categories){ category ->
                Category(
                    category = category,
                    modifier = Modifier
                )
            }
        }

        headerTitle(title = R.string.header_title_1)

        LazyRow(){
            items(DataSource.products){ product ->
                ProductItem(
                    product = product
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier){
    Card() {
        Column() {
            Image(
                painter = painterResource(product.productImageRes) ,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(product.productName)
            )
            Text(
                text = String.format("%.1f", product.productPrice)
            )
            Row() {
                Text(
                    text = stringResource(R.string.see_details)
                )
                Box(
                    modifier = Modifier
                        .background(Color.Green)
                        .clip(RoundedCornerShape(100.dp))
                ){
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Category(category: Category, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(category.imageRes),
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(30.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = stringResource(category.categoryName))
    }
}



@Composable
fun EditText(modifier: Modifier = Modifier){
    TextField(
        value = "" ,
        onValueChange = {},
        label = { Text(text = "Search")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 21.dp)
    )
}
@Composable
fun headerTitle(@StringRes title: Int, modifier: Modifier = Modifier){
    Text(
        text = stringResource(title),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceUITheme {
        HomeScreen()
    }
}