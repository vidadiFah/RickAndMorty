package com.example.rickandmorty.data.repository

import com.example.rickandmorty.core.Either
import com.example.rickandmorty.data.api.CartoonApi
import com.example.rickandmorty.data.mapper.toDomain
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class CharacterRepositoryImpl(
    private val api: CartoonApi
) : CharacterRepository {

    override fun getCharacters() : Flow<Either<String, List<Character>>> {
        return flow {
            delay(1000)
            try {
                val response = api.getCharacters()
                if (response.isSuccessful && response.body() != null){
                    response.body()?.let { result ->
                        emit(Either.Right(result.characters.toDomain()))
                    }
                } else {
                    emit(Either.Left(response.message()))
                }
            } catch (e: Exception) {
                emit(Either.Left(e.localizedMessage ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)
    }

}