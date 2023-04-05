package com.diegoRB.quotes.presentation.screens.update_Quotes

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
class UpdateQuoteViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
    private val quotesUseCases: QuotesUseCases,
    authUseCases: AuthUseCases
): ViewModel() {

    var state by mutableStateOf(UpdateQuoteState())
    val resultingActivityHandler = ResultingActivityHandler()

    //Imagen
    var file: File? = null

    //Argumento recibido
    val data = savedStateHandle.get<String>("quote")
    val quote = Quote.fromJson(data!!)

    //RESPONSE
    var updateQuoteResponse by mutableStateOf<Response<Boolean>?>(null)
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

    init {
        state = state.copy(
            name = quote.name,
            description = quote.description,
            image = quote.image,
            category = quote.category
        )
    }

    fun updateQuote(quote: Quote) = viewModelScope.launch {
        updateQuoteResponse = Response.Loading
        val result = quotesUseCases.update(quote, file)
        updateQuoteResponse = result
    }

    fun onUpdateQuote() {
        val quote = Quote(
            id = quote.id,
            name = state.name,
            description = state.description,
            category = state.category,
            image = quote.image,
            idUser = currentUser?.uid ?: ""
        )
        updateQuote(quote)
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
        updateQuoteResponse = null
    }

}

//Data class que se usa para crear Categorías con un nombre y una imagen asociada
//Si solo lo usamos en el RadioButon, no será necesario establecerla en el Domain
data class CategoryRadioButton(
    var category: String,
    var image: Int
)