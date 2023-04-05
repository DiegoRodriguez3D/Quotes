package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun DefaultBoxHeader(
    size: Int,
    background: androidx.compose.ui.graphics.Color,
    roundness: Int
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = roundness.dp, bottomEnd = roundness.dp))
            .height(size.dp)
            .fillMaxWidth()
            .background(background),
    )
}
