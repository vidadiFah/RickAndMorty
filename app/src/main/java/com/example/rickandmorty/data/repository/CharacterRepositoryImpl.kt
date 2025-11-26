package com.example.rickandmorty.data.repository

import com.example.rickandmorty.core.Either
import com.example.rickandmorty.core.mapEither
import com.example.rickandmorty.data.api.CartoonApi
import com.example.rickandmorty.data.base.BaseRepository
import com.example.rickandmorty.data.mapper.toDomain
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val api: CartoonApi
) : CharacterRepository, BaseRepository() {

    override fun getCharacters() : Flow<Either<String, List<Character>>> {
        return doRequest { api.getCharacters() }.mapEither {
            it.characters.toDomain()
        }
    }

    override fun getCharacterByID(id: Int): Flow<Either<String, Character>> {
        return doRequest { api.getCharacterByID(id) }.mapEither {
            it.toDomain()
        }
    }
}
