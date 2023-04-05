package com.diegoRB.quotes.presentation.screens.profile_update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.use_cases.users.UsersUseCases
import com.diegoRB.quotes.presentation.utils.ComposeFileProvider
import com.diegoRB.quotes.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val usersUseCases: UsersUseCases, @ApplicationContext private val context:Context): ViewModel() {

    //Estado del formulario
    var state by mutableStateOf(ProfileUpdateState())
        private set
    //Variables Nombre
    var isNameValid by mutableStateOf(false)
        private set
    var nameErrMsg by mutableStateOf("")
        private set

    //Parametro pasado por navigation
    val data = savedStateHandle.get<String>("user") //recoje el usuario pasado en forma de String
    val user = User.fromJson(data!!) //Lo convierte a un objeto de tipo USER

    //Variables Response, utilizadas para recoger la respuesta de un evento
    var updateResponse by mutableStateOf<Response<Boolean>?>(null) //Update User
        private set
    var saveImageResponse by mutableStateOf<Response<String>?>(null) //SaveImage
        private set

    //Variables IMAGE
    val resultingActivityHandler = ResultingActivityHandler()

    //Variables FILE
    var file: File? = null


    init {
        state = state.copy(
            name = user.name,
            image = user.image
        )
    }

    //Variables Button
    var isEnabledRegisterButton = false


    //FUNCIONES Inputs
    fun onNameInput(name: String){
        state = state.copy(name = name)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user = user)
        updateResponse = result
    }

    fun onUpdate(url: String){
        val userUpdate = User(
            id= user.id,
            name = state.name,
            image = url
        )
        update(userUpdate)
    }

    fun saveImage() = viewModelScope.launch {
        if(file != null){
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
           state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result != null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    //Validaciones
    fun validateName(){
        if(state.name.isNotEmpty()){
            nameErrMsg = ""
        }
        else{
            nameErrMsg = "Introduzca un nombre"
        }
    }

}