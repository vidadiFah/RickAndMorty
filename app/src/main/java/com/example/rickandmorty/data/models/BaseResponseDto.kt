package com.example.rickandmorty.data.models


import com.google.gson.annotations.SerializedName

data class BaseResponseDto(
    @SerializedName("info")
    val info: InfoDto? = null,
    @SerializedName("results")
    val characters: List<CharacterDto>? = null
)