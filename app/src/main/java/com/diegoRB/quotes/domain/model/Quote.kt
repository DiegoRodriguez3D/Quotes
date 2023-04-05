package com.diegoRB.quotes.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Quote(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var category: String = "",
    var image: String = "",
    var idUser: String = "",
    var user: User? = null,
    var likes: ArrayList<String> = ArrayList()
){
    fun toJson(): String = Gson().toJson(
        Quote(
        id,
        name,
        description,
        category,
        if (image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else "",
        idUser,
        User(
            id=user?.id ?: "",
            name=user?.name ?: "",
            email =user?.email ?: "",
            image = if (!user?.image.isNullOrBlank())
                URLEncoder.encode(user?.image, StandardCharsets.UTF_8.toString())
            else "",

        ),
        likes
    )
    )
    companion object {
        fun fromJson(data: String): Quote = Gson().fromJson(data, Quote::class.java)
    }
}
