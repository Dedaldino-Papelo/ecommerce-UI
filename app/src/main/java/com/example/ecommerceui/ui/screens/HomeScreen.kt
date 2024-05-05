package com.example.ecommerceui.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceui.R
import com.example.ecommerceui.data.DataSource
import com.example.ecommerceui.models.Category
import com.example.ecommerceui.models.Product
import com.example.ecommerceui.models.Recommended

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    onProductClick: (Product) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    var inputValue by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp)
        ) {
            Column() {
                Text(
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(R.string.what_do_you_want),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp
                )
            }
            SearchBar(
                modifier = Modifier,
                value = inputValue,
                onValueChange = { inputValue = it }
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                items(DataSource.categories) { category ->
                    Category(
                        category = category,
                        modifier = Modifier
                    )
                }
            }

            headerTitle(title = R.string.header_title_1)

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(DataSource.products) { product ->
                    ProductItem(
                        product = product,
                        onClick = { onProductClick(product) },
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }

            headerTitle(title = R.string.header_title_2)

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(DataSource.recommended) { recommended ->
                    RecommendedItem(
                        recommendedProd = recommended
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.cardColor)
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                with(sharedTransitionScope) {
                    Image(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image-${product.productId}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                            .size(150.dp),
                        painter = painterResource(product.productImageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(key = "text-${product.productName}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                },
                                enter = fadeIn(),
                                exit = fadeOut()
                            ),
                        text = stringResource(product.productName),
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.displayMedium
                    )
                }
                Text(
                    text = String.format("%.1f", product.productPrice)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.see_details),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    IconButton(
                        onClick = onClick
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .background(colorResource(R.color.default_color), CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendedItem(recommendedProd: Recommended, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(recommendedProd.imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(80.dp)
            .width(100.dp)
    )
}

@Composable
fun Category(category: Category, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .background(colorResource(R.color.cardColor), shape = RoundedCornerShape(15.dp))
                .width(53.dp)
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(category.imageRes),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(category.categoryName),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        label = { Text(text = "Search") },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(R.color.cardColor),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
    )
}

@Composable
fun headerTitle(@StringRes title: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier
            .padding(vertical = 20.dp)
    )
}

/*
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceUITheme {
            HomeScreen(
                onProductClick = {},
            )
    }
}*/
