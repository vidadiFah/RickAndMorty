package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {

    fun getCharacters(): Flow<List<String>> = repository.getCharacters()
}