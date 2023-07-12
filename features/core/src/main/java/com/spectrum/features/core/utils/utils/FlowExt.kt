package com.spectrum.features.core.utils.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectWhenStarted(
    owner: LifecycleOwner,
    crossinline action: suspend (T) -> Unit
): Job {
    return collectWithState(owner, Lifecycle.State.STARTED, action)
}

inline fun <T> Flow<T>.collectWhenResumed(
    owner: LifecycleOwner,
    crossinline action: suspend (T) -> Unit
): Job {
    return collectWithState(owner, Lifecycle.State.RESUMED, action)
}

inline fun <T> Flow<T>.collectWhenCreated(
    owner: LifecycleOwner,
    crossinline action: suspend (T) -> Unit
): Job {
    return collectWithState(owner, Lifecycle.State.CREATED, action)
}

inline fun <T> Flow<T>.collectWithState(
    owner: LifecycleOwner,
    state: Lifecycle.State,
    crossinline action: suspend (T) -> Unit
): Job {
    return owner.lifecycleScope.launch {
        owner.repeatOnLifecycle(state) {
            collect { action(it) }
        }
    }
}
