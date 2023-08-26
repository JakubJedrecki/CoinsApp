package com.jakub.domain.shared

sealed class ResultResponse<T> {
    data class Success<T>(val data: T) : ResultResponse<T>()
    data class Error<T>(val error: ErrorEntity): ResultResponse<T>()
}
