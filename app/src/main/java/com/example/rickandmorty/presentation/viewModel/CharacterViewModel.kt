package com.example.rickandmorty.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val _characterState = MutableStateFlow<List<String>>(emptyList())
    val characterState = _characterState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            getCharacterUseCase.getCharacters().collect { data ->
                _characterState.value = data
            }
        }
    }
}