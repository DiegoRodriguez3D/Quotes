package com.diegoRB.quotes.presentation.screens.my_quotes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.use_cases.auth.AuthUseCases
import com.diegoRB.quotes.domain.use_cases.quotes.QuotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyQuotesViewModel @Inject constructor(
    private val quotesUseCases: QuotesUseCases,
    authUseCases: AuthUseCases
) : ViewModel() {
    var quotesResponse by mutableStateOf<Response<List<Quote>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)
    val currentUser = authUseCases.getCurrentUser()

    init {
        getQuotes()
    }

    fun delete(idQuote: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = quotesUseCases.delete(idQuote)
        deleteResponse = result
    }

    fun getQuotes() = viewModelScope.launch {
        quotesUseCases.getQuotesByIdUser(currentUser?.uid ?: "").collect { response->
            quotesResponse = response
        }
    }
}