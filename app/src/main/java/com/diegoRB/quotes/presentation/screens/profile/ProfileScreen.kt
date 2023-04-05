package com.diegoRB.quotes.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.components.DefaultTopBar
import com.diegoRB.quotes.presentation.screens.profile.components.ProfileContent
import com.diegoRB.quotes.presentation.ui.theme.Red900

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
                 DefaultTopBar(title = "", color = Red900, hasNavBack = false)
                 },
        content = {
                  ProfileContent(navController)
        },
        bottomBar = {}
    )
}