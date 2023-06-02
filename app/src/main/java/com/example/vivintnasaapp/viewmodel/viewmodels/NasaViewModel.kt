package com.example.vivintnasaapp.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vivintnasaapp.model.data.nasaimages.Item
import com.example.vivintnasaapp.model.repositories.NasaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NasaViewModel : ViewModel() {

    private val _nasaCollectionMutableStateFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading) // default state set to loading
    val nasaCollectionStateFlow: StateFlow<UIState> = _nasaCollectionMutableStateFlow

    fun getImages(q: String, description: String) = viewModelScope.launch {
        try {
            // simulate loading state (UIState.Loading) with a simple delay
            delay(1000L)
            val response = NasaRepository.getImages(q, description)
            response?.let {
                // Filter results to show top 10 as per instructions
                _nasaCollectionMutableStateFlow.value = UIState.Success(it.collection.items.subList(0, 10))
            } ?: run {
                UIState.Error("Response object is null")
            }
        } catch (e: Exception) {
            _nasaCollectionMutableStateFlow.value = UIState.Error(e.localizedMessage as String)
        }
    }

    sealed class UIState {
        object Loading : UIState()
        data class Success(val itemsList: List<Item>) : UIState()
        data class Error(val reason: String) : UIState()
    }
}
