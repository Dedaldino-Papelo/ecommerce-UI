package com.example.ecommerceui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceui.data.DataSource
import com.example.ecommerceui.ui.screens.HomeScreen
import com.example.ecommerceui.ui.screens.OrderScreen
import com.example.ecommerceui.ui.screens.ProductDetailScreen

enum class NavigationScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Details(title = R.string.product_details),
    Order(title = R.string.product_order)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcommerceAppBar(
    navController: NavController,
    canNavigateBack: Boolean
){
    TopAppBar(
        title = {  },
        navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Localized description"
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
        )
    )
}

@Composable
fun NavigationApp(navController: NavHostController = rememberNavController()){

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            EcommerceAppBar(
                navController = navController,
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = NavigationScreen.Start.name,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(route = NavigationScreen.Start.name) {
                HomeScreen(
                    onProductClick = { product ->
                        navController.navigate("${NavigationScreen.Details.name}/${product.productId}")
                    }
                )
            }

            composable(route = "${NavigationScreen.Details.name}/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                val product = DataSource.products.find { it.productId == productId }
                if (product != null) {
                    ProductDetailScreen(product = product)
                } else {
                    //Do Something
                }
            }

            composable(route = NavigationScreen.Order.name) {
                OrderScreen()
            }
        }
    }
}