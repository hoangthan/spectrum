package com.spectrum.libraries.core.usecase

sealed interface UseCaseResult<out T> {
    data class Success<T>(val data: T) : UseCaseResult<T>
    data class Failure(val exception: Throwable) : UseCaseResult<Nothing>
}

fun <T> T.asSuccessResult(): UseCaseResult<T> {
    return UseCaseResult.Success(this)
}

fun <T, U> UseCaseResult<T>.mapSuccess(converter: (T) -> U): UseCaseResult<U> {
    return when (this) {
        is UseCaseResult.Failure -> this
        is UseCaseResult.Success -> converter(data).asSuccessResult()
    }
}

suspend fun <T, U> UseCaseResult<T>.mapSuccessSuspend(converter: suspend (T) -> U): UseCaseResult<U> {
    return when (this) {
        is UseCaseResult.Failure -> this
        is UseCaseResult.Success -> converter(data).asSuccessResult()
    }
}
