package com.example.ecommerceclone.ui.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}


enum class Screens{
    REGISTER,LOGIN,HOME,PRODUCTDETAILS
}

fun LifecycleOwner.launchAndRepeatOn(
    state: Lifecycle.State,
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch { repeatOnLifecycle(state, block) }
}

fun <T> Flow<T>.collectWhenStarted(
    owner: LifecycleOwner,
    action: FlowCollector<T>
) {
    owner.launchAndRepeatOn(Lifecycle.State.STARTED) { collect(action) }
}