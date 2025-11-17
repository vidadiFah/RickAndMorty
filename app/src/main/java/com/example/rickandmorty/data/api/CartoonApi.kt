package com.example.rickandmorty.data.api

import retrofit2.http.GET

interface CartoonApi {

    @GET("character")
    suspend fun getCharacters() : List<String>
}