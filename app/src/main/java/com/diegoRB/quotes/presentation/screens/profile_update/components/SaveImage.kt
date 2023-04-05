package com.diegoRB.quotes.presentation.screens.profile_update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.screens.profile_update.ProfileUpdateViewModel

@Composable
fun SaveImage(viewModel: ProfileUpdateViewModel = hiltViewModel()){
    when (val response = viewModel.saveImageResponse){
        Response.Loading -> {
            LoadingProgressBar()
        }
        is Response.Success -> {
            viewModel.onUpdate(response.data) //accede a la url de la imagen ya que se almacena en el data de la response (lo pasamos como String en la respuesta)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}