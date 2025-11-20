package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.core.Either
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {

    fun getCharacters(): Flow<Either<String, List<Character>>> = repository.getCharacters()
}