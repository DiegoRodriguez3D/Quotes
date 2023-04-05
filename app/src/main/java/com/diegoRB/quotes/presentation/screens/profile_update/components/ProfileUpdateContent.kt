package com.diegoRB.quotes.presentation.screens.profile_update.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.diegoRB.quotes.presentation.screens.profile_update.ProfileUpdateViewModel
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun ProfileEditContent(viewModel: ProfileUpdateViewModel = hiltViewModel()) {

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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Red900,
                            Black900,
                            White50
                        ), startY = 400f, endY = 2200f
                    )
                )
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(modifier = Modifier.size(80.dp), painter = painterResource(id = R.drawable.update_user_icon), contentDescription = "", tint = White50)
                    Text(text = "EDITAR USUARIO", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = White50)
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 40.dp)
                    .fillMaxSize(),
                shape = RoundedCornerShape(25.dp),
                backgroundColor = White50,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 30.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Muestra avatar usuario
                    if(viewModel.state.image!=""){
                        AsyncImage(
                            modifier = Modifier
                                .height(115.dp)
                                .width(115.dp)
                                .clip(CircleShape)
                                .clickable { dialogState.value = true },
                            model = viewModel.state.image,
                            contentDescription = "Selected image",
                            contentScale = ContentScale.Crop)
                    }else{
                        //Si no tiene, muestra imagen por defecto
                        Image(
                            modifier= Modifier
                                .size(80.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            painter = painterResource(id = R.drawable.default_avatar),
                            contentDescription = "Default image")
                    }
                    
                    Text(text = "SELECCIONA AVATAR", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    Spacer(modifier = Modifier.height(15.dp))

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.name,
                        onValueChange = {viewModel.onNameInput(it)},
                        label = "Nombre",
                        icon = Icons.Rounded.Person,
                        tipo = KeyboardType.Text,
                        shape = RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp),
                        errorMsg = viewModel.nameErrMsg,
                        validateField = {viewModel.validateName()}
                    )
                }
            }
        }
    }
}