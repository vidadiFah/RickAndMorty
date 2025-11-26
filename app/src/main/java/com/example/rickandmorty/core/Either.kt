package com.example.rickandmorty.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()
}

fun <T, S> Flow<Either<String, T>>.mapEither(
    transform: (T) -> S
): Flow<Either<String, S>> {
    return this.map { either ->
        when(either) {
            is Either.Left -> Either.Left(either.value)
            is Either.Right -> Either.Right(transform(either.value))
        }
    }
}
