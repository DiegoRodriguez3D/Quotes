package com.diegoRB.quotes.domain.repository

import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface QuotesRepository {
    fun getQuotes(): Flow<Response<List<Quote>>>
    fun getQuotesByUserId(idUser:String): Flow<Response<List<Quote>>>
    suspend fun create(quote: Quote, file: File): Response<Boolean>
    suspend fun update(quote: Quote, file: File?): Response<Boolean>
    suspend fun delete(idQuote: String): Response<Boolean>
    suspend fun like(idQuote: String, idUser: String): Response<Boolean>
    suspend fun unLike(idQuote: String, idUser: String): Response<Boolean>
}