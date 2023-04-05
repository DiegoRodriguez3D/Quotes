package com.diegoRB.quotes.data.repository

import android.net.Uri
import com.diegoRB.quotes.core.Constants.QUOTES
import com.diegoRB.quotes.core.Constants.USERS
import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.repository.QuotesRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class QuotesRepositoryImpl @Inject constructor(
    @Named(QUOTES) private val quoteRef: CollectionReference,
    @Named(QUOTES) private val storageQuotesRef: StorageReference,
    @Named(USERS) private val usersRef: CollectionReference
): QuotesRepository {
    override fun getQuotes(): Flow<Response<List<Quote>>> = callbackFlow {
        val snapshotListener = quoteRef.addSnapshotListener{ snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val quoteResponse = if (snapshot != null) {
                    val quotes = snapshot.toObjects(Quote::class.java)

                    snapshot.documents.forEachIndexed { index, document ->
                        quotes[index].id = document.id
                    }

                    val idUserArray = ArrayList<String>()
                    quotes.forEach{ quote ->
                    idUserArray.add(quote.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList() //Elementos sin repetir

                    idUserList.map{id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)!!
                            quotes.forEach { quote ->
                                if(quote.idUser == id){
                                    quote.user = user
                                }
                            }
                        }
                    }.forEach{
                        it.await()
                    }
                    Response.Success(quotes)
                } else {
                    Response.Failure(e)
                }
                trySend(quoteResponse)
            }
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun getQuotesByUserId(idUser: String): Flow<Response<List<Quote>>> = callbackFlow {
        val snapshotListener = quoteRef.whereEqualTo("idUser", idUser).addSnapshotListener{ snapshot, e ->
                val quoteResponse = if (snapshot != null) {
                    val quotes = snapshot.toObjects(Quote::class.java)
                    snapshot.documents.forEachIndexed { index, document ->
                        quotes[index].id = document.id
                    }

                    Response.Success(quotes)
                } else {
                    Response.Failure(e)
                }
                trySend(quoteResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun create(quote: Quote, file: File): Response<Boolean> {
        return try {
            //IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storageQuotesRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            //DATA
            quote.image = url.toString()
            quoteRef.add(quote).await()
            Response.Success(true)

        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(quote: Quote, file: File?): Response<Boolean> {
        return try {
            //IMAGE
            if(file != null){
                val fromFile = Uri.fromFile(file)
                val ref = storageQuotesRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                quote.image = url.toString()
            }

            //Valores a actualizar
            val map: MutableMap<String, Any> = HashMap()
            map["name"] = quote.name
            map["description"] = quote.description
            map["image"] = quote.image
            map["category"] = quote.category

            //DATA
            quoteRef.document(quote.id).update(map).await()
            Response.Success(true)

        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun delete(idQuote: String): Response<Boolean> {
        return try {
            quoteRef.document(idQuote).delete().await()
            Response.Success(true)
        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun like(idQuote: String, idUser: String): Response<Boolean> {
        return try {
            quoteRef.document(idQuote).update("likes", FieldValue.arrayUnion(idUser)).await()
            Response.Success(true)
        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun unLike(idQuote: String, idUser: String): Response<Boolean> {
        return try {
            quoteRef.document(idQuote).update("likes", FieldValue.arrayRemove(idUser)).await()
            Response.Success(true)
        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}