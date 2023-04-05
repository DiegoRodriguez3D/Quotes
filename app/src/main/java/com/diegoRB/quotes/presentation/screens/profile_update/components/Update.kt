package com.diegoRB.quotes.presentation.screens.profile_update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.screens.profile_update.ProfileUpdateViewModel

@Composable
fun Update(viewModel: ProfileUpdateViewModel = hiltViewModel()){
    when(val updateResponse = viewModel.updateResponse) {
        Response.Loading -> {
            LoadingProgressBar()
        }

        is Response.Success -> {
            Toast.makeText(LocalContext.current, "Datos actualizados correctamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateResponse.exception?.message ?: "Error desconocido.", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}