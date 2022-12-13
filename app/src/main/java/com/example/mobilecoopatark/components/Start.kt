package com.example.mobilecoopatark.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilecoopatark.Routes
import com.example.mobilecoopatark.viewmodels.ModalCollectViewModel
import com.example.mobilecoopatark.viewmodels.ModalViewModel

@Composable
fun Start(activity: ComponentActivity, viewModel: ModalViewModel, viewModelCollect: ModalCollectViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginPage(navController = navController, activity = activity)
        }
        composable(Routes.MainPage.route) {
            MainPage(navController = navController, activity = activity)
        }
    }
}