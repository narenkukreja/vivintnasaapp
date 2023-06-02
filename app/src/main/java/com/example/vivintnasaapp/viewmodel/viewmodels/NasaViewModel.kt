package com.example.vivintnasaapp.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vivintnasaapp.model.data.nasaimages.Item
import com.example.vivintnasaapp.model.repositories.NasaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NasaViewModel : ViewModel() {

    private val _nasaImageMutableStateFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val nasaImageStateFlow: StateFlow<UIState> = _nasaImageMutableStateFlow

    fun getImages(q: String, description: String) = viewModelScope.launch {
        try {
            _nasaImageMutableStateFlow.value = UIState.Loading
            val response = NasaRepository.getImages(q, description)
            response?.let {
                _nasaImageMutableStateFlow.value = UIState.Success(it.collection.items.subList(0, 10))
            } ?: run {
                UIState.Error("Response object is null")
            }
        } catch (e: Exception) {
            _nasaImageMutableStateFlow.value = UIState.Error(e.localizedMessage as String)
        }
    }

    sealed class UIState {
        object Loading : UIState()
        data class Success(val itemsList: List<Item>) : UIState()
        data class Error(val reason: String) : UIState()
    }
}
