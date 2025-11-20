package com.example.rickandmorty.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.Either
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val _characterState = MutableStateFlow<List<Character>>(emptyList())
    val characterState = _characterState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _loaderState = MutableStateFlow(false)
    val loaderState = _loaderState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            getCharacterUseCase.getCharacters()
                .onStart { _loaderState.value = true }
                .collect { result ->
                when(result) {
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