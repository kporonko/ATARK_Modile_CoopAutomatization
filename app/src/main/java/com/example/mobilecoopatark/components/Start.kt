package com.example.mobilecoopatark.components

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = Routes.MainPage.route,
            arguments = listOf(navArgument("profileId"){
                type = NavType.IntType
            })
        ) {
            Log.d("Args", it.arguments?.getInt("profileId").toString())
            MainPage(navController = navController, activity = activity, it.arguments?.getInt("profileId")!!.toInt())
        }
    }
}