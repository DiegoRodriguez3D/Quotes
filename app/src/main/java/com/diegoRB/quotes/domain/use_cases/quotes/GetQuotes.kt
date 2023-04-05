package com.diegoRB.quotes.domain.use_cases.quotes

import com.diegoRB.quotes.domain.repository.QuotesRepository
import javax.inject.Inject

class GetQuotes @Inject constructor(private val repository: QuotesRepository){
    operator fun invoke() = repository.getQuotes()
}