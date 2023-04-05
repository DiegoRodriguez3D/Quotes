package com.diegoRB.quotes.presentation.screens.quotes.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.presentation.screens.quotes.components.QuotesCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostsContent(
    navController: NavHostController,
    posts: List<Quote>
) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp, top = 8.dp, bottom = 68.dp)

                ){
                    items(
                        items = posts
                    ){post ->
                        QuotesCard(navController, posts = post)
                    }
                }
}