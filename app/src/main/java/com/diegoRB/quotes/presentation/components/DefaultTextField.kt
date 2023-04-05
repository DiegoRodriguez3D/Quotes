package com.diegoRB.quotes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diegoRB.quotes.presentation.ui.theme.Red700


@Composable
fun DefaultTextField(
    modifier: Modifier, //Utilizado para personalizar los modificadores
    value: String, // Valor que obtiene cuando el usuario introduce algo en el campo de texto
    onValueChange: (value:String) -> Unit, // Metodo obligatorio, para actualizar el contenido cuando el usuario introduce datos
    validateField: () -> Unit = {},
    label: String, //Establece el nombre del textfield
    icon: ImageVector, //Establece el icono
    tipo: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false, //Lo utilizaremos para ocultar caracteres (password)
    errorMsg: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp), //Utilizado para redondear esquinas, por defecto desactivado

    ) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            singleLine = singleLine,
            maxLines = maxLines,
            onValueChange = {
                onValueChange(it)
                validateField() },
            shape = shape,
            keyboardOptions = KeyboardOptions(
                keyboardType = tipo,
                imeAction = ImeAction.Next
            ),
            label = {
                Text(text = label)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary
                )
            },
            visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None
        )
        
        Text(
            text = errorMsg,
            modifier = Modifier.padding(top = 3.dp),
            fontSize = 11.sp,
            color = Red700
        )
    }
}