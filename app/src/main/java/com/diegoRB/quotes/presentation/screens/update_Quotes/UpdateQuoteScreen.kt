package com.diegoRB.quotes.presentation.screens.update_Quotes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.components.DefaultButton
import com.diegoRB.quotes.presentation.components.DefaultTopBar
import com.diegoRB.quotes.presentation.screens.update_Quotes.components.UpdateQuote
import com.diegoRB.quotes.presentation.screens.update_Quotes.components.UpdateQuotesContent
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateQuoteScreen(navController: NavHostController,
                      quote: String, viewModel: UpdateQuoteViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "",
                     hasNavBack = true,
                     navController = navController,
                     color = Red900
                 )
        },
        content = { UpdateQuotesContent() },
        bottomBar = {
            Card(backgroundColor = White50) {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    text = "GUARDAR",
                    onClick = { viewModel.onUpdateQuote() },
                    icon = null )
            }
        }
    )
    UpdateQuote()
}