package com.diegoRB.quotes.domain.use_cases.auth

//Almacena todos los casos de uso relacionados con la Autentificación
data class AuthUseCases (
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val singup: SingUp
    )