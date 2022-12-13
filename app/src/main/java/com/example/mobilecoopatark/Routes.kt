package com.example.mobilecoopatark

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object MainPage : Routes("Main")

}