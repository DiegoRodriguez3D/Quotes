package com.diegoRB.quotes.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoRB.quotes.domain.model.User
import com.diegoRB.quotes.domain.use_cases.auth.AuthUseCases
import com.diegoRB.quotes.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases) : ViewModel() {

    var userData by mutableStateOf(User())
    private set

    val currenUser = authUseCases.getCurrentUser() //Obtiene el id del usuario logeado

    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(currenUser!!.uid).collect {//Obtiene los datos del usuario logeado
            userData = it
        }
    }

    fun logout() {
        authUseCases.logout()
    }

}