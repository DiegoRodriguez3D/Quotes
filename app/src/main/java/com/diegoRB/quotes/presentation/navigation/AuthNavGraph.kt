package com.diegoRB.quotes.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.diegoRB.quotes.presentation.screens.login.LoginScreen
import com.diegoRB.quotes.presentation.screens.singup.SingupScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController) //Ponemos la screen correspondiente
        }

        composable(route = AuthScreen.Singup.route){
            SingupScreen(navController)
        }
    }
}

sealed class AuthScreen(val route:String) {
    object Login: AuthScreen("login")
    object Singup: AuthScreen("singup")
}