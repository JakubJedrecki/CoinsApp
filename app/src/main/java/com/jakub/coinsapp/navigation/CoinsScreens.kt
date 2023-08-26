package com.jakub.coinsapp.navigation

enum class CoinsScreens {
    HomeScreen;

    companion object {
        fun fromRoute(route: String?): CoinsScreens =
            when(route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}