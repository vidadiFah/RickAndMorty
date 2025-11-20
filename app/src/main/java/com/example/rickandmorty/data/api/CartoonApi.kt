package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.models.BaseResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CartoonApi {

    @GET("character")
    suspend fun getCharacters() : Response<BaseResponseDto>
}