package com.diegoRB.quotes.domain.repository

import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    //Utilizaremos corrutinas ya que no sabemos cuando el server dará respuesta
    //Por tanto responderemos a ese evento con una funcion suspend
    //El metodo creará un usuario, por lo que le pasamos el usuario de Auth por parametro
    //Responderá con un boolean, en caso de que lo cree correctamente o lance error
    suspend fun create(user: User): Response<Boolean>

    suspend fun update(user: User): Response<Boolean>
    suspend fun saveImage(file: File): Response<String>


    //Al utilizar Flow (Corrutinas) nos devuelve información actualizada de Firestore en tiempo real!
    //Al usar Flow no es necesario utilizar suspend
    fun getUserById(id: String): Flow<User>
}