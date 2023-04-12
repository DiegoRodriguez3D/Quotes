package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun DefaultButton( //Introduzco parámetros en la función, de forma que pueda reutilizarlo y configurarlo
    modifier: Modifier,
    text: String,
    onClick: () -> Unit, //El método onclick será una funcion lambda
    icon: ImageVector?, //Por defecto asignará este icono
    enabled: Boolean = true,
    color: Color = Red900,
    textColor: Color = White50

) {
    Column {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            enabled = enabled
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    tint = textColor,
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            }
        }
    }
}