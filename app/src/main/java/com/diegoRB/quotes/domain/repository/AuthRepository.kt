package com.diegoRB.quotes.domain.repository

import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    //Creamos una variable para el usuario actual
    //Será de tipo Firebase
    //LLeva ? para indicar que puede ser null
    val currentUser: FirebaseUser?

    //Creamos una funcion login
    //Añadimos suspend para poder trabajar con corrutinas
    //Recibe por parametros el email y la contraseña
    //Devuelve una respuesta(clase Response de model) de tipo FirebaseUser
    suspend fun login(email: String, password:String): Response<FirebaseUser>

    //La funcion singUp utilizada para registrar usuarios, recibe el user por parametro y devuelve una respuesta
    suspend fun singUp(user: User): Response<FirebaseUser>

    //La funcion logout se utiliza para cerrar sesion
    fun logout()

}