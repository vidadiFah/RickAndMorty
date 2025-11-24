package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.core.Either
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterByIdUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Either<String, Character>> = repository.getCharacterByID(id)
}
