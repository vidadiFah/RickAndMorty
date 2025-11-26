package com.example.rickandmorty.presentation.viewModel

import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.usecases.GetCharacterUseCase
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
): BaseViewModel() {

    private val _characterState = MutableStateFlow<UiState<List<Character>>>(UiState.Empty)
    val characterState = _characterState.asStateFlow()

    fun getCharacters() {
        getCharacterUseCase.getCharacters().handleFlowData(_characterState)

    }
}