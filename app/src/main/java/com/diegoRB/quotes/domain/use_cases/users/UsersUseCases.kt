package com.diegoRB.quotes.domain.use_cases.users

import com.diegoRB.quotes.domain.use_cases.users.Create
import com.diegoRB.quotes.domain.use_cases.users.GetUserById
import com.diegoRB.quotes.domain.use_cases.users.SaveImage
import com.diegoRB.quotes.domain.use_cases.users.Update

data class UsersUseCases(
    val create: Create,
    val getUserById: GetUserById,
    val update: Update,
    val saveImage: SaveImage
)
