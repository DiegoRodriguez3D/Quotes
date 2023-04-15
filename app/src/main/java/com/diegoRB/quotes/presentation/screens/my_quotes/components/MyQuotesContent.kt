package com.diegoRB.quotes.presentation.screens.my_quotes.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.diegoRB.quotes.domain.model.Quote

@Composable
fun MyQuotesContent(
    navController: NavHostController,
    quotes: List<Quote>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 8.dp, bottom = 60.dp)

    ) {
        items(
            items = quotes

        ) { quote ->
            MyQuotesCard(navController, quote = quote)
        }
    }

}