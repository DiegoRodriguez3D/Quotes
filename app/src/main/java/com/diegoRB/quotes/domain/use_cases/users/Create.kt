package com.diegoRB.quotes.domain.use_cases.users

import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: User) = repository.create(user)
}