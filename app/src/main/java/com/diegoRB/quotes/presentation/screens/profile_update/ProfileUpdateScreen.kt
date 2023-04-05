package com.diegoRB.quotes.presentation.screens.profile_update

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
import com.diegoRB.quotes.presentation.components.DefaultTopBarCentered
import com.diegoRB.quotes.presentation.screens.profile_update.components.ProfileEditContent
import com.diegoRB.quotes.presentation.screens.profile_update.components.SaveImage
import com.diegoRB.quotes.presentation.screens.profile_update.components.Update
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(navController: NavHostController, viewModel: ProfileUpdateViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            DefaultTopBarCentered(
                title = "",
                hasNavBack = true,
                navController = navController,
                color = Red900,
                contentColor = White50
            )
        },
        content = {
            ProfileEditContent()
        },
        bottomBar = {
            Card(backgroundColor = White50) {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    text = "GUARDAR",
                    icon = null,
                    onClick = { viewModel.saveImage()},
                )
            }
        }
    )
    SaveImage()
    Update()
}