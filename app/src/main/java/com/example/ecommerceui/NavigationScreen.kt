package com.example.ecommerceui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceui.ui.screens.HomeScreen
import com.example.ecommerceui.ui.screens.ProductDetails

enum class NavigationScreen(){
    Start,
    Details
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcommerceAppBar(){
    TopAppBar(
        title = { Text(text = "Home") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun NavigationApp(navController: NavHostController = rememberNavController()){
    Scaffold(
        topBar = {
            EcommerceAppBar()
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = NavigationScreen.Start.name,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            composable(route = NavigationScreen.Start.name){
                HomeScreen()
            }

            composable(route = NavigationScreen.Details.name){
                ProductDetails()
            }
        }
    }
}