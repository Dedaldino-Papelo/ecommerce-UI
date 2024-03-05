package com.example.ecommerceui.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceui.data.Category
import com.example.ecommerceui.data.DataSource
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceUITheme {
        HomeScreen()
    }
}