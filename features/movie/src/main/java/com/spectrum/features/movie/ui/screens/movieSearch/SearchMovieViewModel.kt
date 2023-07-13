package com.spectrum.features.movie.ui.screens.movieSearch

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListViewModel
import com.spectrum.features.movie.ui.screens.movieSearch.SearchMovieViewModel.ViewEvent.UpdateSearchName
import com.spectrum.libraries.movie.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapNotNull
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

    private val searchParamState = MutableStateFlow(UpdateSearchName())

    private val moviePagingFlow = searchParamState
        .debounce(KEYWORD_DEBOUNCE_TIME)
        .mapNotNull { it.name }
        .flatMapLatest {
            val pagingSource = SearchMoviePagingSource(it, searchMovieUseCase)
            Pager(pageConfig) { pagingSource }.flow
        }
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    override fun getMoviePagingFlow() = moviePagingFlow

    fun dispatchEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is UpdateSearchName -> searchParamState.update { it.copy(name = viewEvent.name) }
        }
    }

    sealed interface ViewEvent {
        data class UpdateSearchName(val name: String? = null) : ViewEvent
    }

    companion object {
        const val KEYWORD_DEBOUNCE_TIME = 500L
    }
}
