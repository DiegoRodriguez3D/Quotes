package com.diegoRB.quotes.domain.use_cases.users

import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: User) = repository.update(user)
}