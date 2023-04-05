package com.diegoRB.quotes.presentation.screens.quotes

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.components.DefaultTopBarCentered
import com.diegoRB.quotes.presentation.screens.quotes.components.GetQuotes
import com.diegoRB.quotes.presentation.screens.quotes.components.LikePost
import com.diegoRB.quotes.presentation.screens.quotes.components.UnlikePost
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController, viewModel: QuotesViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { DefaultTopBarCentered(title = "Quotes", hasNavBack = false, color = White50, contentColor = Black900) },
        content = {
            GetQuotes(navController)
        }
    )
    LikePost()
    UnlikePost()
}