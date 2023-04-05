package com.diegoRB.quotes.domain.use_cases.users

import com.diegoRB.quotes.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke(id:String) = repository.getUserById(id)
}