package com.diegoRB.quotes.presentation.screens.update_Quotes.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.screens.update_Quotes.UpdateQuoteViewModel

@Composable
fun UpdateQuote(viewModel: UpdateQuoteViewModel = hiltViewModel()) {
    when(val response = viewModel.updateQuoteResponse) {
        Response.Loading -> {
            LoadingProgressBar()
        }

        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current, "La publicación se ha actualizado correctamente.", Toast.LENGTH_LONG).show()
        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}