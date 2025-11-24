package com.example.rickandmorty.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.Either
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailCharacterViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _characterState = MutableStateFlow<Character?>(null)
    val characterState = _characterState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _loaderState = MutableStateFlow(false)
    val loaderState = _loaderState.asStateFlow()

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase(id)
                .onStart { _loaderState.value = true }
                .collect { result ->
                    when (result) {
                        is Either.Left -> {
                            _errorState.value = result.value
                            _loaderState.value = false
                        }
                        is Either.Right -> {
                            _characterState.value = result.value
                            _loaderState.value = false
                        }
                    }
                }
        }
    }
}
