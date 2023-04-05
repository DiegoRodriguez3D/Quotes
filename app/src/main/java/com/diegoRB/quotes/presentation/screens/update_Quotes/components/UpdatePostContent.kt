package com.diegoRB.quotes.presentation.screens.update_Quotes.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.diegoRB.quotes.R
import com.diegoRB.quotes.presentation.components.DefaultTextField
import com.diegoRB.quotes.presentation.components.DialogCapturePicture
import com.diegoRB.quotes.presentation.screens.update_Quotes.UpdateQuoteViewModel
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.Grey500
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun UpdatePostContent(viewModel: UpdateQuoteViewModel = hiltViewModel()) {

    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()

    val dialogState = remember {
        mutableStateOf(false)
    }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = {viewModel.takePhoto()},
        pickImage = {viewModel.pickImage()}
    )

    Box(modifier = Modifier.fillMaxWidth()){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Red900,
                            Black900,
                            White50
                        ), startY = 400f, endY = 2200f
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)

            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(viewModel.state.image!=""){
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { dialogState.value = true },
                                model = viewModel.state.image,
                                contentDescription = "Selected image",
                                contentScale = ContentScale.Crop,
                            )
                    }else{
                        //Si no hay imagen seleccionada, muestra la de por defecto
                        Image(
                            modifier= Modifier
                                .size(120.dp)
                                .padding(20.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            painter = painterResource(id = R.drawable.add_img_icon),
                            contentDescription = "Default image",
                            colorFilter = ColorFilter.tint(White50)
                        )
                        Text(text = "SELECCIONA UNA IMAGEN", color = White50)
                    }

                }
            }

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 60.dp),
                shape = RoundedCornerShape(25.dp),
                backgroundColor = White50,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(modifier = Modifier.align(Alignment.Start), text = "Editar Quote", fontSize = 42.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    DefaultTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.name,
                        onValueChange = {viewModel.onNameInput(it)},
                        label = "Nombre",
                        icon = Icons.Rounded.Create,
                        tipo = KeyboardType.Text,
                        shape = RoundedCornerShape(topStart = 12.dp),
                        errorMsg = "",
                        validateField = {}
                    )

                    DefaultTextField(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth(),
                        value = state.description,
                        onValueChange = {viewModel.onDescriptionInput(it)},
                        label = "Descripción",
                        icon = Icons.Rounded.List,
                        tipo = KeyboardType.Text,
                        shape = RoundedCornerShape(bottomEnd = 12.dp),
                        errorMsg = "",
                        singleLine = false,
                        maxLines = 10,
                        validateField = {}
                    )
                    
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .width(110.dp),
                        text = "CATEGORIA",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    viewModel.radioOptions.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .selectable(
                                    selected = (option.category == state.category),
                                    onClick = { viewModel.onCategoryInput(option.category) }
                                )
                                .border(
                                    if (option.category == state.category) BorderStroke(
                                        2.dp,
                                        Red900
                                    ) else BorderStroke(2.dp, Grey500), RoundedCornerShape(15.dp)
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            RadioButton(
                                selected = (option.category == state.category),
                                onClick = { viewModel.onCategoryInput(option.category)},
                                colors = RadioButtonDefaults.colors(selectedColor = Red900)
                            )
                            Row(
                                modifier = Modifier
                                    .height(50.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.width(100.dp),
                                    text = option.category
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Image(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(id = option.image),
                                    contentDescription = "",
                                    colorFilter = ColorFilter.tint(Black900)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}