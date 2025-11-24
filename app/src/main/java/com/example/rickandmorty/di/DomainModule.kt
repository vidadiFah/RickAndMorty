package com.example.rickandmorty.di

import com.example.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import com.example.rickandmorty.domain.usecases.GetCharacterUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCharacterUseCase(repository = get()) }
    single { GetCharacterByIdUseCase(repository = get()) }
}
