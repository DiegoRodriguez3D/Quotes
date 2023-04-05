package com.diegoRB.quotes.presentation.screens.detail_quote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.diegoRB.quotes.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailQuoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val data = savedStateHandle.get<String>("quote")
    val quote = Quote.fromJson(data!!)
}