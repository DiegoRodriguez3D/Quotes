package com.diegoRB.quotes.presentation.screens.singup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.use_cases.auth.AuthUseCases
import com.diegoRB.quotes.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {
    //Estado del formulario
    var state by mutableStateOf(SingupState())
        private set

    //Variables Nombre
    var isNameValid by mutableStateOf(false)
        private set
    var nameErrMsg by mutableStateOf("")
        private set

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

    //Variables Confirmar Contraseña
    var isConfirmPassValid by mutableStateOf(false)
        private set
    var confirmPassErrMsg by mutableStateOf("")
        private set

    //Variables Button
    var isEnabledRegisterButton = false

    //Variables Singup Firebase
    //SINGUP RESPONSE
    var singupResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    //Variables Firestore
    var user = User()

    //FUNCIONES Inputs
    fun onNameInput(name: String){
        state = state.copy(name = name)
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }


    //REGISTRO
    //La funcion onSingup asigna los valores introducidos por el usuario a un objeto user que usaremos para registrar en Firebase Auth
    //Y a continuacion invoca la funcion sinup para registrarlo
    fun onSingup(){
        user.name = state.name
        user.email = state.email
        user.password = state.password
        singup(user)
    }

    //Esta función asigna el id de Firebase Auth al objeto user, y lo registra en la BD Firestore en la coleccion Users
    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid //Asigna el id de Firebase Auth y crea el usuario
        usersUseCases.create(user)
    }

    fun singup(user: User) = viewModelScope.launch {
        singupResponse = Response.Loading
        val result = authUseCases.singup(user)
        singupResponse = result
    }

    //Validaciones
    fun validateName(){
        if(state.name.isNotEmpty()){
            isNameValid = true
            nameErrMsg = ""
        }
        else{
            isNameValid = false
            nameErrMsg = "Introduzca un nombre"
        }

        enableRegisterButton()
    }

    fun validateEmail() {
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        }
        else {
            isEmailValid = false
            emailErrMsg = "El email introducido no es válido."
        }
        enableRegisterButton()
    }

    fun validatePassword() {
        if(state.password.length >= 6){ //Firebase requiere contraseñas de almenos 6 caracteres
            isPassValid = true
            passErrMsg = ""
        }
        else {
            isPassValid = false
            passErrMsg = "Debe de contener como mínimo 6 caracteres."
        }
        enableRegisterButton()
    }

    fun validateConfirmPassword(){
        if(state.password == state.confirmPassword){
            isConfirmPassValid = true
            confirmPassErrMsg = ""
        }
        else{
            isConfirmPassValid = false
            confirmPassErrMsg = "La contraseña no coincide."
        }

        enableRegisterButton()
    }

    fun enableRegisterButton(){
        isEnabledRegisterButton = isEmailValid
                    && isPassValid
                    && isNameValid
                    && isConfirmPassValid
    }
}