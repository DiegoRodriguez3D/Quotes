package com.diegoRB.quotes.data.repository

import android.net.Uri
import com.diegoRB.quotes.core.Constants.USERS
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.diegoRB.quotes.domain.model.User
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(USERS) private val storageUsersRef: StorageReference
): UsersRepository {

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = "" //Evita guardar la contraseña del usuario en la BD
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        }
        catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["name"] = user.name
            map["image"] = user.image
            usersRef.document(user.id).update(map).await()
            Response.Success(true)
        }
        catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return Response.Success(url.toString())

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow{
        //Escuchador de la informacion que envia Firestore
        //Apunta al id del usuario objetivo
        val snapshotListener = usersRef.document(id).addSnapshotListener{ snapshot, e ->
            //Convierte la informacion obtenida en un objeto User. En caso de que sea nulo, (:?) crea un usuario vacio
            val user = snapshot?.toObject(User::class.java) ?: User()

            //Emite la informacion en tiempo real utilizando callbackFlow!
            trySend(user)
        }

        //Elimina los objetos innecesarios que se crean al escuchar con snapshot (Evita consumo de recursos)
        awaitClose {
            snapshotListener.remove()
        }
    }
}