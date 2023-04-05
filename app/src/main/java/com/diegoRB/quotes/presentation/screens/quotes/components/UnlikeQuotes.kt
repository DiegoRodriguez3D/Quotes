package com.diegoRB.quotes.presentation.screens.quotes.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.screens.quotes.QuotesViewModel

@Composable
fun UnlikePost(viewModel: QuotesViewModel = hiltViewModel()) {
    when(val response = viewModel.unlikeQuotesResponse) {
        Response.Loading -> { //Muestra que la petición se está realizando con el simbolo de carga
            LoadingProgressBar()
        }
        is Response.Success -> {

        }
        is Response.Failure -> { //Devuelve un mensaje cuando el inicio de sesion NO es correcto
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}