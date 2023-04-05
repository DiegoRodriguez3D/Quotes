package com.diegoRB.quotes.presentation.screens.my_quotes.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.screens.my_quotes.MyQuotesViewModel
import com.diegoRB.quotes.presentation.screens.my_quotes.components.MyQuotesContent

@Composable
fun GetQuotesByIdUser(navController: NavHostController, viewModel: MyQuotesViewModel = hiltViewModel()) {
    when(val response = viewModel.quotesResponse) {
        Response.Loading -> {
            LoadingProgressBar()
        }
        is Response.Success -> {
            MyQuotesContent(navController, quotes = response.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}