package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diegoRB.quotes.presentation.ui.theme.White200

@Composable
fun DefaultVerticalDivider(
    pad: Int = 0,
    color: Color = White200,
    grosor: Int = 1
) {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = pad.dp)
            .width(grosor.dp),
        color = color,
    )
}