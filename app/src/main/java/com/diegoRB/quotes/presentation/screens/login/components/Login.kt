package com.diegoRB.quotes.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.presentation.components.LoadingProgressBar
import com.diegoRB.quotes.presentation.navigation.Graph
import com.diegoRB.quotes.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    when(val loginResponse = viewModel.loginResponse) {
        Response.Loading -> { //Muestra que la petición se está realizando con el simbolo de carga
            LoadingProgressBar()
        }
        is Response.Success -> { //Cuando la respuesta es exitosa, nos envia a otra pantalla
            //Como estamos evaluando un estado, deberemos de usar un LaunchEffect para navegar a otra pantalla
            LaunchedEffect(Unit){
                navController.navigate(route = Graph.HOME){
                    //Eliminamos Login del historial de pantallas, para que el usuario no pueda volver atrás una vez iniciada la sesion!
                    popUpTo(Graph.AUTH){inclusive=true}
                }
            }
        }
        is Response.Failure -> { //Devuelve un mensaje cuando el inicio de sesion NO es correcto
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}