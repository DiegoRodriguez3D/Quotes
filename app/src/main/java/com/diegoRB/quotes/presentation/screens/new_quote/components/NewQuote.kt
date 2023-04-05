package com.diegoRB.quotes.presentation.screens.new_quote.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.screens.new_quote.NewQuoteViewModel

@Composable
fun NewQuote(viewModel: NewQuoteViewModel = hiltViewModel()) {
    when(val response = viewModel.createQuoteResponse) {
        Response.Loading -> { //Muestra que la petición se está realizando con el simbolo de carga
            LoadingProgressBar()
        }

        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current, "La publicación se ha creado correctamente.", Toast.LENGTH_LONG).show()
        }

        is Response.Failure -> { //Devuelve un mensaje cuando el inicio de sesion NO es correcto
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}