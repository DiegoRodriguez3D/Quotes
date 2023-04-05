package com.diegoRB.quotes.domain.use_cases.quotes

import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.domain.repository.QuotesRepository
import java.io.File
import javax.inject.Inject

class UpdateQuote @Inject constructor(private val repository: QuotesRepository){
    suspend operator fun invoke(quote: Quote, file: File?) = repository.update(quote,file)
}