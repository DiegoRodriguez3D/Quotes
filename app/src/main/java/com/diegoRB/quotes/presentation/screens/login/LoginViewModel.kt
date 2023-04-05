package com.diegoRB.quotes.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.use_cases.auth.AuthUseCases
import com.diegoRB.quotes.presentation.screens.login.LoginState
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {
    //Estado del formulario
    var state by mutableStateOf(LoginState())
        private set //Indica que el metodo setter es privado para que no podamos modificarlo desde otras clases.

    //Variables email
    var isEmailValid by mutableStateOf(false)
        private set

    var emailErrMsg by mutableStateOf("")
        private set

    //Variables contraseña
    var isPassValid by mutableStateOf(false)
        private set

    var passErrMsg by mutableStateOf("")
        private set

    //Variables Button
    var isEnabledLoginButton = false

    //Variables Firebase
    //LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    val currentUser = authUseCases.getCurrentUser()

    init {
        if(currentUser != null){ //Sesion Iniciada
           loginResponse = Response.Success(currentUser)
        }
    }

    //Funciones Input
    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    //Validaciones
    fun validateEmail() {
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        }
        else {
            isEmailValid = false
            emailErrMsg = "El email introducido no es válido."
        }
        enableLoginButton()
    }

    fun validatePassword() {
        if(state.password.length >= 6){ //Firebase requiere contraseñas de almenos 6 caracteres
            isPassValid = true
            passErrMsg = ""
        }
        else {
            isPassValid = false
            passErrMsg = "Debe de contener como mínimo 6 caracteres"
        }
        enableLoginButton()
    }

    fun enableLoginButton(){
        isEnabledLoginButton = isEmailValid && isPassValid
    }

    //Login
    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(email = state.email, password = state.password)
        loginResponse = result
    }
}