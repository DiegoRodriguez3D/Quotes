package com.diegoRB.quotes.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.screens.login.LoginViewModel
import com.diegoRB.quotes.presentation.components.DefaultButton
import com.diegoRB.quotes.presentation.components.DefaultTopBar
import com.diegoRB.quotes.presentation.screens.login.components.Login
import com.diegoRB.quotes.presentation.screens.login.components.LoginContent
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "",
                     hasNavBack = false,
                     color = Red900
                 )
        },
        content = {
            Column {
                LoginContent(navController)
            }
        },
        bottomBar = {
            Card(backgroundColor = White50) {
                viewModel.state
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    text = "INICIAR SESIÓN",
                    icon = null,
                    onClick = { viewModel.login()},
                    enabled = viewModel.isEnabledLoginButton
                )
            }
        }
    )
    Login(navController = navController)
}