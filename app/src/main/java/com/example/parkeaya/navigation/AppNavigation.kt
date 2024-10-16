package com.example.parkeaya.navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.parkeaya.screens.MainScreen
import com.example.parkeaya.screens.HistoryScreen
import com.example.parkeaya.screens.UpdatesScreen
import com.example.parkeaya.screens.UserDataScreen
import com.example.parkeaya.screens.FinesScreen
import com.example.parkeaya.screens.PaymentMethodsScreen
import com.example.parkeaya.screens.SupportScreen
import androidx.compose.runtime.Composable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.HistoryScreen.route) {
            HistoryScreen(navController = navController)
        }

        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = AppScreens.UpdatesScreen.route) {
            UpdatesScreen(navController = navController)
        }

        composable(route = AppScreens.UserDataScreen.route) {
            UserDataScreen(navController = navController)
        }

        composable(route = AppScreens.FinesScreen.route) {
            FinesScreen(navController = navController)
        }

        composable(route = AppScreens.PaymentMethodsScreen.route) {
            PaymentMethodsScreen(navController = navController)
        }

        composable(route = AppScreens.SupportScreen.route) {
            SupportScreen(navController = navController)
        }
    }
}


