package com.spectrum.libraries.core.usecase

sealed interface UseCaseResult<out T> {
    data class Success<T>(val data: T) : UseCaseResult<T>
    data class Failure(val exception: Throwable? = null) : UseCaseResult<Nothing>
}

