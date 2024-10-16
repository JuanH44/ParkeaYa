package com.example.parkeaya.navigation

sealed class AppScreens(val route: String) {
    object HistoryScreen : AppScreens("history_screen")
    object MainScreen : AppScreens("main_screen")
    object UpdatesScreen : AppScreens("updates_screen")
    object UserDataScreen : AppScreens("user_data_screen")
    object FinesScreen : AppScreens("fines_screen")
    object PaymentMethodsScreen : AppScreens("payment_methods_screen")
    object SupportScreen : AppScreens("support_screen")
}