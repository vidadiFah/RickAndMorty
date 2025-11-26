package com.example.rickandmorty.presentation.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmorty.presentation.utils.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseActivity: AppCompatActivity() {

    protected fun <T> StateFlow<UiState<T>>.handleState(
        onSuccess: (data: T) -> Unit,
        onLoading: (isLoading: Boolean) -> Unit
    ){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@handleState.collect { state ->
                    onLoading(state is UiState.Loading)
                    when (state) {
                        UiState.Empty -> {}

                        is UiState.Error -> {
                            Toast.makeText(this@BaseActivity, state.message, Toast.LENGTH_SHORT).show()
                        }

                        UiState.Loading -> {}

                        is UiState.Success -> {
                            onSuccess(state.data)
                        }
                    }
                }
            }
        }
    }
}