package com.diegoRB.quotes.presentation.screens.singup.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.navigation.AuthScreen
import com.diegoRB.quotes.presentation.ui.theme.Red500

@Composable
fun SingupBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿Ya tienes cuenta?",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            //Usamos el modificador clickable para que el texto sea un enlace
            modifier = Modifier.clickable {
                //Al clicar, lo enviamos a la screen correspondiente
                navController.navigate(route = AuthScreen.Login.route)
            },
            text = "INICIA SESIÓN",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Red500
        )
    }
}


