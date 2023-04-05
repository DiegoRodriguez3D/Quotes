package com.diegoRB.quotes.domain.use_cases.quotes

data class QuotesUseCases(
    val create: CreateQuote,
    val update: UpdateQuote,
    val delete: DeleteQuote,
    val getQuotes: GetQuotes,
    val getQuotesByIdUser: GetQuotesByIdUser,
    val likeQuote: LikeQuote,
    val unlikeQuote: UnlikeQuote
)