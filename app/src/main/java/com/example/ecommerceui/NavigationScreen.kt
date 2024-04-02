package com.example.ecommerceui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceui.data.DataSource
import com.example.ecommerceui.ui.screens.HomeScreen
import com.example.ecommerceui.ui.screens.HomeViewModel
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
    currentScreen: NavigationScreen,
    navigateUp: () -> Unit,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            } else {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(R.string.menu)
                    )
                }
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
            containerColor =
            if(currentScreen.title !== R.string.app_name)
                colorResource(R.color.default_color)
            else Color.Transparent
        )
    )
}

@Composable
fun NavigationApp(){
    val viewModel: HomeViewModel = viewModel()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationScreen.valueOf(
        navBackStackEntry?.destination?.route ?: NavigationScreen.Start.name
    )

    Scaffold(
        topBar = {
            EcommerceAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiSTate.collectAsState()

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
                        viewModel.setProductById(product.productId)
                        navController.navigate(NavigationScreen.Details.name)
                    }
                )
            }

            composable(route = NavigationScreen.Details.name) { backStackEntry ->
                val productId = uiState.productId
                val product = DataSource.products.find { it.productId == productId }
                if (product != null) {
                    ProductDetailScreen(product = product)
                } else {
                }
            }

            composable(route = NavigationScreen.Order.name) {
                OrderScreen()
            }
        }
    }
}