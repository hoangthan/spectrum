package com.spectrum.libraries.core.usecase

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<in T, out V> {
    fun execute(param: T): Flow<UseCaseResult<V>>
}
