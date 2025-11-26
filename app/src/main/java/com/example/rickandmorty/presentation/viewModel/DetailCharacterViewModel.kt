package com.example.rickandmorty.presentation.viewModel

import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailCharacterViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel() {

    private val _characterState = MutableStateFlow<UiState<Character>>(UiState.Empty)
    val characterState = _characterState.asStateFlow()

    fun getCharacterById(id: Int) {
        getCharacterByIdUseCase(id).handleFlowData(_characterState)
    }
}
