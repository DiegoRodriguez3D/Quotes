package com.diegoRB.quotes.presentation.screens.my_quotes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.diegoRB.quotes.R
import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.presentation.components.DefaultVerticalDivider
import com.diegoRB.quotes.presentation.components.DialogDeleteQuote
import com.diegoRB.quotes.presentation.navigation.DetailsScreen
import com.diegoRB.quotes.presentation.screens.my_quotes.MyQuotesViewModel
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun MyQuotesCard(navController: NavHostController, quote: Quote, viewModel: MyQuotesViewModel = hiltViewModel()) {

    val dialogState = remember {
        mutableStateOf(false)
    }

    DialogDeleteQuote(
        status = dialogState,
        deleteQuote = {viewModel.delete(idQuote = quote.id)},
        dismiss = {}
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Black900
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .clickable {navController.navigate(route = DetailsScreen.DetailQuote.sendQuote(quote.toJson()))},
            ){
                AsyncImage(
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    model = quote.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f
                )

                Column(modifier = Modifier
                    .height(350.dp)
                    .padding(20.dp),  verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = quote.name, fontWeight = FontWeight.Bold, maxLines = 1, fontSize = 20.sp, color = MaterialTheme.colors.background)

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "`" + quote.description + "´", fontSize = 18.sp, fontStyle = FontStyle.Italic, textAlign = TextAlign.Justify, maxLines = 5, color = MaterialTheme.colors.background)
                    }
                }
            }

            Row(
                modifier = Modifier
                  .height(70.dp)
                    .fillMaxWidth()
                  .background(White50)
                  .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(
                                route = DetailsScreen.UpdateQuote.sendQuote(
                                    quote.toJson()
                                )
                            )
                        }
                    ,
                    elevation = 0.dp,
                    shape = RoundedCornerShape(15.dp), backgroundColor = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                            Icon(
                                modifier = Modifier.height(20.dp),
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = "",
                            )
                        
                        Spacer(modifier = Modifier.width(5.dp))


                        Text(text = "Editar", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onPrimary)
                    }
                }

                DefaultVerticalDivider(pad=10)

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                    ,
                    elevation = 0.dp,
                    shape = RoundedCornerShape(15.dp), backgroundColor = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(
                                id = if (quote.category == "Deportes") R.drawable.sports_icon
                                else if (quote.category == "Salud") R.drawable.health_icon
                                else if (quote.category == "Productividad") R.drawable.productivity_icon
                                else R.drawable.add_img_icon
                            ),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = quote.category, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onPrimary)
                    }
                }

                DefaultVerticalDivider(pad=10)

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            dialogState.value=true
                        }
                    ,
                    elevation = 0.dp,
                    shape = RoundedCornerShape(15.dp), backgroundColor = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                            Icon(
                                modifier = Modifier.height(20.dp),
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "",
                                tint = Red900
                            )
                        
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(text = "Borrar", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onPrimary)
                    }
                }
            }
        }
    }
}