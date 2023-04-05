package com.diegoRB.quotes.domain.use_cases.auth

import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.repository.AuthRepository
import javax.inject.Inject

class SingUp @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.singUp(user)
}