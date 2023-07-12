package com.spectrum.libraries.network.utils

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.core.usecase.asSuccessResult
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend fun <T> ApiResponse<T>.toUseCaseResult(): UseCaseResult<T> {
    return suspendCancellableCoroutine { continuation ->
        onSuccess {
            continuation.resume(data.asSuccessResult())
        }
        onError {//Logic exception HTTP 4xx, 5xx...
            val errorBody = response.errorBody()?.string()
            val result = UseCaseResult.Failure(Exception(errorBody))
            continuation.resume(result)
        }
        onException {//Connection error: NoInternetConnection,...
            continuation.resume(UseCaseResult.Failure(exception))
        }
    }
}
