package com.example.ecommerceclone.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecommerceclone.ui.utils.Screens


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HOME.toString()) {
        composable(route = Screens.REGISTER.toString()) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screens.LOGIN.toString()) {
            LoginScreen(navController = navController)
        }
        composable(route = Screens.HOME.toString()) {
            HomeScreen {
                navController.navigate(
                    Screens.PRODUCTDETAILS.toString()+"/?productId=${it.id}"
                )
            }
        }
        composable(route = "${Screens.PRODUCTDETAILS}/?productId={productId}", arguments = listOf(
            navArgument("productId"){
                type = NavType.IntType
            }
        )) {
            ProductDetailsScreen(it.arguments?.getInt("productId") ?: 0)
        }
    }
}