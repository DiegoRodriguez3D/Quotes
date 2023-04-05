package com.diegoRB.quotes.presentation.screens.detail_quote.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.diegoRB.quotes.presentation.screens.detail_quote.DetailQuoteViewModel
import com.diegoRB.quotes.presentation.ui.theme.Grey500
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.R


@Composable
fun DetailQuoteContent(viewModel: DetailQuoteViewModel = hiltViewModel()) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp))
            ,
            model = viewModel.quote.image,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
            Text(modifier = Modifier.padding(5.dp), text = viewModel.quote.name, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(10.dp))
            
            Row(modifier = Modifier.fillMaxWidth()) {
                if(!viewModel.quote.user?.name.isNullOrBlank()){
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp)
                            .height(45.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Row(modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(35.dp)
                                    .clip(CircleShape),
                                model = viewModel.quote.user?.image,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = viewModel.quote.user?.name ?: "",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }else{
                    Box {
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp)
                        .height(45.dp)
                    ,
                    elevation = 4.dp,
                    shape = RoundedCornerShape(15.dp), backgroundColor = Red900
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(
                                id = if (viewModel.quote.category == "Deportes")
                                    R.drawable.sports_icon
                                else if (viewModel.quote.category == "Salud") R.drawable.health_icon
                                else if (viewModel.quote.category == "Productividad") R.drawable.productivity_icon
                                else R.drawable.add_img_icon
                            ),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = viewModel.quote.category, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }

        //Barra separador
        Divider(modifier = Modifier.padding(end = 20.dp, top = 20.dp, bottom = 10.dp), startIndent = 20.dp, thickness = 1.dp, color = Grey500)

        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
            Text(text = "Descripción", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = viewModel.quote.description, fontSize = 14.sp)
        }
    }
}