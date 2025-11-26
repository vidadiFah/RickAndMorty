package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.models.BaseResponseDto
import com.example.rickandmorty.data.models.CharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApi {

    @GET("character")
    suspend fun getCharacters() : Response<BaseResponseDto>

    @GET("character/{id}")
    suspend fun getCharacterByID(
        @Path("id")
        id: Int) : Response<CharacterDto>
}
