package com.thanhng224.app.core.util

data class UiState<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: Throwable? = null
) {
    val isSuccess: Boolean get() = !loading && data != null && error == null
    val isError: Boolean get() = !loading && error != null
    val isLoading: Boolean get() = loading

    companion object {
        fun <T> loading(): UiState<T> = UiState(loading = true)
        fun <T> success(data: T): UiState<T> = UiState(data = data)
        fun <T> error(error: Throwable): UiState<T> = UiState(error = error)
    }
}

