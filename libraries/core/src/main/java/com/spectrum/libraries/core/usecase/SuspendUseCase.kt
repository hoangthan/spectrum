package com.spectrum.libraries.core.usecase

interface SuspendUseCase<in T, out V> {
    suspend fun execute(param: T): UseCaseResult<V>
}
