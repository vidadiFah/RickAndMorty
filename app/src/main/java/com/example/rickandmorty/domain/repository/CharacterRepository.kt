package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.core.Either
import com.example.rickandmorty.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters() : Flow<Either<String, List<Character>>>
}