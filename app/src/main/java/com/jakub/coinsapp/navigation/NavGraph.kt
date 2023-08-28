package com.jakub.coinsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jakub.coinsapp.features.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = CoinsScreens.HomeScreen.name) {

        composable(CoinsScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
    }
}