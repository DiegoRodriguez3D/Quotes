package com.diegoRB.quotes.presentation.screens.quotes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.diegoRB.quotes.presentation.navigation.DetailsScreen
import com.diegoRB.quotes.presentation.screens.quotes.QuotesViewModel
import com.diegoRB.quotes.presentation.ui.theme.Black900
import com.diegoRB.quotes.presentation.ui.theme.Red900
import com.diegoRB.quotes.presentation.ui.theme.White50

@Composable
fun QuotesCard(
    navController: NavHostController,
    quotes: Quote,
    viewModel: QuotesViewModel = hiltViewModel()
) {
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
                    .clickable {
                        navController.navigate(
                            route = DetailsScreen.DetailQuote.sendQuote(
                                quotes.toJson()
                            )
                        )
                    },
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    model = quotes.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f
                )

                Column(
                    modifier = Modifier
                        .height(250.dp)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = quotes.name,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.background
                        )

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = quotes.description,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Justify,
                            maxLines = 6,
                            color = MaterialTheme.colors.background
                        )
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
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (!quotes.user?.name.isNullOrBlank()) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = Color.Transparent
                    ) {
                        Row(
                            modifier = Modifier.padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape),
                                model = quotes.user?.image,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = quotes.user?.name ?: "",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                } else {
                    Box {

                    }
                }

                DefaultVerticalDivider(pad = 10)

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(15.dp), backgroundColor = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(
                                id = if (quotes.category == "Deportes") R.drawable.sports_icon
                                else if (quotes.category == "Salud") R.drawable.health_icon
                                else if (quotes.category == "Productividad") R.drawable.productivity_icon
                                else R.drawable.add_img_icon
                            ),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = quotes.category,
                            fontSize = 15.sp,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }

                DefaultVerticalDivider(pad = 10)

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            if (quotes.likes.contains(viewModel.currentUser?.uid))
                                viewModel.unlike(quotes.id, viewModel.currentUser?.uid ?: "")
                            else
                                viewModel.like(quotes.id, viewModel.currentUser?.uid ?: "")
                        },
                    elevation = 0.dp,
                    shape = RoundedCornerShape(15.dp), backgroundColor = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (quotes.likes.contains(viewModel.currentUser?.uid)) {
                            Icon(
                                modifier = Modifier.height(35.dp),
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = "",
                                tint = Red900
                            )
                        } else {
                            Icon(
                                modifier = Modifier.height(35.dp),
                                imageVector = Icons.Rounded.FavoriteBorder,
                                contentDescription = "",
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = quotes.likes.size.toString(),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}