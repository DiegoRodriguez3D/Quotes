package com.diegoRB.quotes.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.diegoRB.quotes.presentation.MainActivity
import com.diegoRB.quotes.presentation.components.DefaultButton
import com.diegoRB.quotes.presentation.navigation.DetailsScreen
import com.diegoRB.quotes.presentation.screens.profile.ProfileViewModel
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    val activity = LocalContext.current as? Activity

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Red900,
                            Black900,
                            White50
                        ), startY = 0f, endY = 2900f
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)

            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(viewModel.userData.image != ""){
                        AsyncImage(
                            modifier = Modifier
                                .size(115.dp)
                                .clip(CircleShape)
                            ,
                            model = viewModel.userData.image,
                            contentDescription = "User Avatar",
                            contentScale = ContentScale.Crop
                        )
                    }else{
                        Image(
                            modifier= Modifier.size(115.dp),
                            imageVector = Icons.Rounded.AccountBox,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(White50)
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = viewModel.userData.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White50
                    )

                    Text(
                        text = viewModel.userData.email,
                        fontSize = 15.sp,
                        color = White50
                    )

                }
            }

            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 40.dp)
                ,
                shape = RoundedCornerShape(25.dp),
                backgroundColor = White50,
                elevation = 0.dp
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 30.dp, horizontal = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "PERFIL", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Black900, maxLines = 1)

                    Spacer(modifier = Modifier.height(20.dp))

                    DefaultButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "USUARIO",
                        onClick = {
                            navController.navigate(route = DetailsScreen.ProfileUpdate.sendUser(viewModel.userData.toJson())) },
                        icon = Icons.Rounded.Edit
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    DefaultButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "LOGOUT",
                        icon = Icons.Rounded.ExitToApp,
                        onClick = {
                            viewModel.logout()
                            activity?.finish()
                            activity?.startActivity(Intent(activity, MainActivity::class.java))
                        })
                }
                }
            }
        }
    }