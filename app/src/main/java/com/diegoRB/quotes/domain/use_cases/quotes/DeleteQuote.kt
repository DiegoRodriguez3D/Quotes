package com.diegoRB.quotes.domain.use_cases.quotes

import com.diegoRB.quotes.domain.repository.QuotesRepository
import javax.inject.Inject

class DeleteQuote @Inject constructor(private val repository: QuotesRepository){
    suspend operator fun invoke(idQuote: String) = repository.delete(idQuote)
}