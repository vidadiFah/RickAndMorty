package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.CartoonApi
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers

class CharacterRepositoryImpl(
    private val api: CartoonApi
) : CharacterRepository {

    override fun getCharacters() : Flow<List<String>> {
        return flow {
            try {
                val response = api.getCharacters()
                emit(response)
            } catch (e: Exception) {

            }
        }.flowOn(Dispatchers.IO)
    }

}