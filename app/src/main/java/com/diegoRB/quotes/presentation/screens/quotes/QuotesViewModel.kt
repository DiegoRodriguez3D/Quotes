package com.diegoRB.quotes.presentation.screens.quotes

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
class QuotesViewModel @Inject constructor(
    private val quotesUseCases: QuotesUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {
    var quotesResponse by mutableStateOf<Response<List<Quote>>?>(null)
    var likeQuotesResponse by mutableStateOf<Response<Boolean>?>(null)
    var unlikeQuotesResponse by mutableStateOf<Response<Boolean>?>(null)
    var currentUser = authUseCases.getCurrentUser()

    init {
        getQuotes()
    }

    fun like(idQuote: String, idUser: String) = viewModelScope.launch {
        likeQuotesResponse = Response.Loading
        val result = quotesUseCases.likeQuote(idQuote, idUser)
        likeQuotesResponse = result
    }

    fun unlike(idQuote: String, idUser: String) = viewModelScope.launch {
        unlikeQuotesResponse = Response.Loading
        val result = quotesUseCases.unlikeQuote(idQuote, idUser)
        unlikeQuotesResponse = result
    }

    fun getQuotes() = viewModelScope.launch {
        quotesUseCases.getQuotes().collect { response->
            quotesResponse = response
        }
    }
}