package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun DefaultTopBar(
    title: String,
    hasNavBack: Boolean = false,
    navController: NavHostController? = null,
    color: Color = White50,
    contentColor: Color = White50
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {Text(text = title, fontSize =14.sp, color = contentColor)},
        backgroundColor = color,
        elevation = 0.dp,
        navigationIcon = {
            if(hasNavBack){
                IconButton(onClick = { navController?.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "",
                    tint = contentColor)
                }
            }
        })
}