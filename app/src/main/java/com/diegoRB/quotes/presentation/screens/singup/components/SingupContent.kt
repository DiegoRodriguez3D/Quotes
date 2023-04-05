package com.diegoRB.quotes.presentation.screens.singup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.R
import com.diegoRB.quotes.presentation.components.DefaultTextField
import com.diegoRB.quotes.presentation.screens.singup.SingupViewModel
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun SingupContent(navController: NavHostController,viewModel: SingupViewModel = hiltViewModel()) {

    val state = viewModel.state


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Red900,
                            Black900,
                            White50
                        ), startY = 400f, endY = 2200f
                    )
                )
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(modifier = Modifier.size(80.dp), painter = painterResource(id = R.drawable.register_icon), contentDescription = "", tint = White50)
                    Text(text = "REGISTRO", fontSize = 42.sp, fontWeight = FontWeight.Bold, color = White50)
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 40.dp)
                    .fillMaxSize(),
                shape = RoundedCornerShape(25.dp),
                backgroundColor = White50,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 30.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.name,
                        onValueChange = {viewModel.onNameInput(it)},
                        label = "Nombre",
                        icon = Icons.Rounded.Person,
                        tipo = KeyboardType.Text,
                        shape = RoundedCornerShape(topStart = 12.dp),
                        errorMsg = viewModel.nameErrMsg,
                        validateField = {viewModel.validateName()}
                    )

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.email,
                        onValueChange = {viewModel.onEmailInput(it)},
                        label = "Email",
                        icon = Icons.Rounded.Email,
                        tipo = KeyboardType.Email,
                        errorMsg = viewModel.emailErrMsg,
                        validateField = {viewModel.validateEmail()}
                    )

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.password,
                        onValueChange = {viewModel.onPasswordInput(it)},
                        label = "Contraseña",
                        icon = Icons.Rounded.Lock,
                        tipo = KeyboardType.Password,
                        hideText = true,
                        errorMsg = viewModel.passErrMsg,
                        validateField = {viewModel.validatePassword()}
                    )

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.confirmPassword,
                        onValueChange = {viewModel.onConfirmPasswordInput(it)},
                        label = "Confirmar Contraseña",
                        icon = Icons.Outlined.Lock,
                        tipo = KeyboardType.Password,
                        hideText = true,
                        shape = RoundedCornerShape(bottomEnd = 12.dp),
                        errorMsg = viewModel.confirmPassErrMsg,
                        validateField = {viewModel.validateConfirmPassword()}
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    SingupBottomBar(navController = navController)
                }
            }
        }
    }
}