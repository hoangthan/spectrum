package com.spectrum.features.movie.ui.screens.movieSearch

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListViewModel
import com.spectrum.libraries.movie.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(
    FlowPreview::class,
    ExperimentalCoroutinesApi::class,
)
@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : AbstractMovieListViewModel() {

    private val searchParamState = MutableStateFlow(ViewEvent.UpdateSearchName())

    private val moviePagingFlow = searchParamState
        .debounce(500)
        .flatMapLatest {
            val pagingSource = SearchMoviePagingSource(it.name, searchMovieUseCase)
            Pager(pageConfig) { pagingSource }.flow
        }
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    override fun getMoviePagingFlow() = moviePagingFlow

    fun dispatchEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is ViewEvent.UpdateSearchName -> searchParamState.update { it.copy(name = viewEvent.name) }
        }
    }

    sealed interface ViewEvent {
        data class UpdateSearchName(val name: String = "") : ViewEvent
    }
}
