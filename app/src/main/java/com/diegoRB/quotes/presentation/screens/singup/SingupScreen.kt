package com.diegoRB.quotes.presentation.screens.singup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.components.DefaultButton
import com.diegoRB.quotes.presentation.screens.singup.components.Singup
import com.diegoRB.quotes.presentation.screens.singup.components.SingupContent
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SingupScreen(navController: NavHostController, viewModel: SingupViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
        },
        content = {
                SingupContent(navController)
        },
        bottomBar = {
            Card(backgroundColor = White50) {
                viewModel.state
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    text = "REGISTRARME",
                    icon = null,
                    onClick = { viewModel.onSingup()},
                    enabled = viewModel.isEnabledRegisterButton
                )
            }
        }
    )
    Singup(navController = navController)
}