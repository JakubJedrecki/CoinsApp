package com.jakub.domain.shared

sealed class ErrorEntity(val message: String) {
    class NetworkFailure(message: String = "network failure") : ErrorEntity(message)
    class Generic(message: String = "Generic error") : ErrorEntity(message)
    class Unknown(message: String = "Unknown error") : ErrorEntity(message)
}
