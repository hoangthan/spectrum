package com.spectrum.features.movie.ui.screens.movieList

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListViewModel
import com.spectrum.libraries.movie.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : AbstractMovieListViewModel() {

    private val filterOptionStateFlow = MutableStateFlow<MovieScreen?>(null)

    private val moviePagingFlow = filterOptionStateFlow
        .filterNotNull()
        .map { it.toMovieSource() }
        .flatMapLatest {
            val pagingSource = MovieListPagingSource(it, getMovieUseCase)
            Pager(pageConfig) { pagingSource }.flow
        }
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)

    override fun getMoviePagingFlow() = moviePagingFlow

    fun dispatchEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.UpdateType -> filterOptionStateFlow.update { event.movieType }
        }
    }

    sealed interface ViewEvent {
        data class UpdateType(val movieType: MovieScreen) : ViewEvent
    }
}
