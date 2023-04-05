package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun DialogDeleteQuote(
    status: MutableState<Boolean>,
    deleteQuote: () -> Unit,
    dismiss: () -> Unit
) {
    if(status.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, White50), RoundedCornerShape(15.dp))
            ,
            onDismissRequest = { status.value = false },
            backgroundColor = White50,
            title = {
                Text(text = "¿Desea eliminar la publicación?", fontSize = 16.sp, color = Black900)
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = { status.value = false; deleteQuote() } //El status.value=false oculta el dialogo al seleccionar una de las opciones!
                    ) {
                        Text(text = "Si", color = White50)
                    }
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = { status.value = false; dismiss() })
                    {
                        Text(text = "No", color = White50)
                    }
                }
            }
        )
    }
}