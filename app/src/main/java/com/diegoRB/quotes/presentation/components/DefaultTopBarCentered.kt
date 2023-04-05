package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun DefaultTopBarCentered(
    title: String,
    hasNavBack: Boolean = false,
    navController: NavHostController? = null,
    color: Color = White50,
    contentColor: Color = MaterialTheme.colors.onPrimary
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = color,
        elevation = 0.dp
    ) {

        Box(modifier = Modifier.height(32.dp)){
            //Icono
            Row(modifier = Modifier
                .fillMaxHeight()
                .width(72.dp)) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    if(hasNavBack){
                        IconButton(onClick = { navController?.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "",
                                tint = contentColor)
                        }
                    }
                }
            }

            //Titulo
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                ProvideTextStyle(value =  MaterialTheme.typography.h6) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = title,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = contentColor,
                            fontSize = 28.sp
                        )
                    }
                }

            }
        }
    }
}