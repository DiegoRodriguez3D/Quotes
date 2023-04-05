package com.diegoRB.quotes.presentation.screens.new_quote

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoRB.quotes.R
import com.diegoRB.quotes.domain.model.Quote
import com.diegoRB.quotes.domain.model.Response
import com.diegoRB.quotes.domain.use_cases.auth.AuthUseCases
import com.diegoRB.quotes.domain.use_cases.quotes.QuotesUseCases
import com.diegoRB.quotes.presentation.utils.ComposeFileProvider
import com.diegoRB.quotes.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewQuoteViewModel @Inject constructor(@ApplicationContext private val context: Context, private val quoteUseCases: QuotesUseCases, authUseCases: AuthUseCases): ViewModel() {

    var state by mutableStateOf(NewQuoteState())
    val resultingActivityHandler = ResultingActivityHandler()

    //Variables FILE
    var file: File? = null

    //RESPONSE
    var createQuoteResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //USER SESSION
    val currentUser = authUseCases.getCurrentUser()

    //Controla las opciones que se muestran en el menú de Categorías
    val radioOptions = listOf(
        CategoryRadioButton("Deportes", R.drawable.sports_icon),
        CategoryRadioButton("Productividad", R.drawable.productivity_icon),
        CategoryRadioButton("Salud", R.drawable.health_icon),
    )

    //Metodos input
    fun onNameInput(name: String){
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String){
        state = state.copy(category = category)
    }

    fun onDescriptionInput(description: String){
        state = state.copy(description = description)
    }

    fun onImageInput(image: String){
        state = state.copy(image = image)
    }

    fun createQuote(quote: Quote) = viewModelScope.launch {
        createQuoteResponse = Response.Loading
        val result = quoteUseCases.create(quote, file!!)
        createQuoteResponse = result
    }

    fun onNewQuote() {
        val quote = Quote(
            name = state.name,
            description = state.description,
            category = state.category,
            idUser = currentUser?.uid ?: ""
        )
        createQuote(quote)
    }

    //Metodos Imagenes
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

    fun clearForm(){
        state = state.copy(
            name = "",
            description = "",
            category = "",
            image = "",
        )
        createQuoteResponse = null
    }

}

//Data class que se usa para crear Categorías con un nombre y una imagen asociada
data class CategoryRadioButton(
    var category: String,
    var image: Int
)