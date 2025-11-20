package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.models.CharacterDto
import com.example.rickandmorty.data.models.LocationDto
import com.example.rickandmorty.data.models.OriginDto
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.models.Location
import com.example.rickandmorty.domain.models.Origin

fun CharacterDto?.toDomain(): Character {
    return Character(
        created = this?.created.orEmpty(),
        episode = this?.episode?: emptyList(),
        gender = this?.gender.orEmpty(),
        id = this?.id ?: 0,
        image = this?.image.orEmpty(),
        location = this?.location.toDomain(),
        name = this?.name.orEmpty(),
        origin = this?.origin.toDomain(),
        species = this?.species.orEmpty(),
        status = this?.status.orEmpty(),
        type = this?.type.orEmpty(),
        url = this?.url.orEmpty()
    )
}

fun LocationDto?.toDomain(): Location {
    return Location(
        name = this?.name.orEmpty(),
        url = this?.url.orEmpty()
    )
}

fun OriginDto?.toDomain(): Origin {
    return Origin(
        name = this?.name.orEmpty(),
        url = this?.url.orEmpty()
    )
}

fun List<CharacterDto>?.toDomain(): List<Character> {
    return this?.map { it.toDomain() } ?: emptyList()
}
