package com.diegoRB.quotes.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.diegoRB.quotes.presentation.screens.detail_quote.DetailQuoteScreen
import com.diegoRB.quotes.presentation.screens.profile_update.ProfileEditScreen
import com.diegoRB.quotes.presentation.screens.new_quote.NewQuoteScreen
import com.diegoRB.quotes.presentation.screens.update_Quotes.UpdateQuoteScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ){
        composable(
            route = DetailsScreen.NewQuote.route
        ){
                NewQuoteScreen(navController = navController)
        }

        composable(
            route = DetailsScreen.ProfileUpdate.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })

        ){
            it.arguments?.getString("user")?.let { user ->
                ProfileEditScreen(navController)
            }
        }

        composable(
            route = DetailsScreen.DetailQuote.route,
            arguments = listOf(navArgument("quote"){
                type = NavType.StringType
            })

        ){
            it.arguments?.getString("quote")?.let { quote ->
                DetailQuoteScreen(navController)
            }
        }
        composable(
            route = DetailsScreen.UpdateQuote.route,
            arguments = listOf(navArgument("quote"){
                type = NavType.StringType
            })

        ){
            it.arguments?.getString("quote")?.let { quote ->
                UpdateQuoteScreen(navController, quote)
            }
        }
    }
}

sealed class DetailsScreen(val route:String) {
    object NewQuote: DetailsScreen("quote/new")

    object ProfileUpdate: DetailsScreen("profile/edit/{user}") {
        fun sendUser(user: String) = "profile/edit/$user"
    }
    object DetailQuote: DetailsScreen("quotes/detail/{quote}") {
        fun sendQuote(quote: String) = "quotes/detail/$quote"
    }
    object UpdateQuote: DetailsScreen("quotes/update/{quote}") {
        fun sendQuote(quote: String) = "quotes/update/$quote"
    }
}