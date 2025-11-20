package com.example.rickandmorty.di

import com.example.rickandmorty.presentation.viewModel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        CharacterViewModel (
            getCharacterUseCase = get()
        )
    }
}