package com.diegoRB.quotes.data.repository

import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//Implementa la interfaz del domain.repository
//Sobreescribe sus metodos y añade firebaseAuth por inyección de dependencias
class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth):
    AuthRepository {

    override val currentUser: FirebaseUser?get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            //Usamos corrutinas para recibir la respuesta con await
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)

        } catch (e: Exception) {
            //En caso de que de error lo envía en la respuesta
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun singUp(user: User): Response<FirebaseUser> {
        return try {
            //Usamos corrutinas para esperar la respuesta con un await
            val result = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            Response.Success(result.user!!)

        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}