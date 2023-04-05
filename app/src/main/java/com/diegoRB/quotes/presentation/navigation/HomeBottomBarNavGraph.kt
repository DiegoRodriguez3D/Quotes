package com.diegoRB.quotes.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diegoRB.quotes.presentation.screens.my_quotes.MyQuotesScreen
import com.diegoRB.quotes.presentation.screens.quotes.PostsScreen
import com.diegoRB.quotes.presentation.screens.profile.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Quotes.route
    ){

        composable(route = HomeBottomBarScreen.Profile.route){
            ProfileScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Quotes.route){
            PostsScreen(navController)
        }
        composable(route = HomeBottomBarScreen.MyQuotes.route){
            MyQuotesScreen(navController)
        }
        detailsNavGraph(navController=navController)
    }
}

//Cotrola el BottomNavBar
sealed class HomeBottomBarScreen(
    val route: String, //ruta
    var title: String, //Nombre del botón
    val icon: ImageVector //Icono del botón
) {
    object Quotes: HomeBottomBarScreen(
        route = "quotes",
        title = "Quotes",
        icon = Icons.Rounded.List
    )

    object MyQuotes: HomeBottomBarScreen(
        route = "my_quotes",
        title = "Mis Quotes",
        icon = Icons.Rounded.Menu
    )

    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Rounded.Person
    )
}