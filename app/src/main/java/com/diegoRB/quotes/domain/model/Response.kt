package com.diegoRB.quotes.domain.model

//Clase utilizada para las respuestas de Firebase
//Como no podemos controlar la respuesta de Firebase,
//la respuesta será de tipo <out T> que es un genérico
sealed class Response<out T>{

    //Creamos un objeto Loading para controlar la carga de los datos
    //Desde que enviamos la petición hasta que la devuelve
    //Es de tipo Nothing para indicar que desconocemos el tipo
    object Loading: Response<Nothing>()

    //Creamos una data class Success, que controlara la respuesta afirmativa
    //Es una respuesta de tipo generico, y recibe un parametro tambien generico
    data class Success<out T>(val data: T): Response<T>()

    //Creamos una data class Failure, que controlará la respuesta negativa
    //Recibe un parametro de tipo exception para el mensaje de error
    //Indicamos con ? que es opcional
    data class Failure<out T>(val exception: Exception?): Response<T>()
}