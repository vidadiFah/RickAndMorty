package com.example.rickandmorty.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.Either
import com.example.rickandmorty.presentation.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Either<String, T>>.handleFlowData(
        state: MutableStateFlow<UiState<T>>
    ) {
        viewModelScope.launch {
            this@handleFlowData
                .onStart { state.value = UiState.Loading }
                .collect { result ->
                    when (result) {
                        is Either.Left -> {
                            state.value = UiState.Error(result.value)
                        }

                        is Either.Right -> {
                            state.value = UiState.Success(result.value)
                        }
                    }
                }
        }
    }

}