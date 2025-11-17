package com.example.rickandmorty.domain.repository

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters() : Flow<List<String>>
}