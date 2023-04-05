package com.diegoRB.quotes.domain.use_cases.quotes

import com.diegoRB.quotes.domain.repository.QuotesRepository
import javax.inject.Inject

class UnlikeQuote @Inject constructor(private val repository: QuotesRepository){
    suspend operator fun invoke(idQuote: String, idUser: String) = repository.unLike(idQuote, idUser)
}