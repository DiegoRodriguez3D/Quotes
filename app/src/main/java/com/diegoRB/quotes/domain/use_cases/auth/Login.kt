package com.diegoRB.quotes.domain.use_cases.auth

import com.diegoRB.quotes.domain.repository.AuthRepository
import javax.inject.Inject

//Controla el caso de uso de logear un usuario
//Inyectamos por dependencia AuthRepository en una variable repository declarada en el constructor
class Login @Inject constructor(private val repository: AuthRepository) {

    //Al utilizar operator invoke la funcion se lanzará automaticamente al llamar a la clase
    //Utilizamos la variable inyectada para llamar a la función login de AuthRepository
    suspend operator fun invoke(email: String, password:String) = repository.login(email, password)
}