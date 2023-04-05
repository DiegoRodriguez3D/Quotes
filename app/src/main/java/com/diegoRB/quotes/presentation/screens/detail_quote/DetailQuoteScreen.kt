package com.diegoRB.quotes.presentation.screens.detail_quote

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.components.DefaultTopBarCentered
import com.diegoRB.quotes.presentation.screens.detail_quote.components.DetailQuoteContent
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailQuoteScreen(navController: NavHostController, viewModel: DetailQuoteViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { DefaultTopBarCentered(
            title = "",
            hasNavBack = true,
            navController,
            color = White50,
            contentColor = Black900
        ) } ,
        content = {
                  DetailQuoteContent()
        },
        bottomBar = {}
    )
}