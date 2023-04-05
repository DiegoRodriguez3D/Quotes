package com.diegoRB.quotes.domain.use_cases.auth

import com.diegoRB.quotes.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.currentUser

}