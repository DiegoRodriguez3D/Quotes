package com.diegoRB.quotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diegoRB.quotes.presentation.screens.home.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH, //Especifica la pantalla por defecto al abrir la app
    ){
        authNavGraph(navController = navController)
        composable(route = Graph.HOME){
            HomeScreen()
        }
    }
}