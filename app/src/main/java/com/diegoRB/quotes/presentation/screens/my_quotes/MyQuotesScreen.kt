package com.diegoRB.quotes.presentation.screens.my_quotes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.components.DefaultTopBarCentered
import com.diegoRB.quotes.presentation.navigation.DetailsScreen
import com.diegoRB.quotes.presentation.screens.my_quotes.components.GetQuotesByIdUser
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.White50

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun MyQuotesScreen(navController: NavHostController) {

    Scaffold(
        topBar = { DefaultTopBarCentered(title = "Mis Quotes", contentColor = Black900, color = White50, hasNavBack = false) },
        content = {
            GetQuotesByIdUser(navController = navController)
        },

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .size(70.dp),
                onClick = {navController.navigate(DetailsScreen.NewQuote.route)}
            ) {

                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "",
                    tint = White50
                )

            }
        }
    )
}