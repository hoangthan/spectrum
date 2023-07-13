package com.spectrum.features.movie.ui.screens.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spectrum.features.movie.ui.components.MovieDetailsUiModel
import com.spectrum.features.movie.ui.components.toUi
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.usecase.GetMovieDetailsUseCase
import com.spectrum.libraries.movie.domain.usecase.GetMovieDetailsUseCase.GetMovieDetailsParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    private val _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    fun dispatchEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.LoadMovie -> loadMovie(event.id)
            is ViewEvent.FavouriteMovie -> favourite(event.id)
            is ViewEvent.UnFavouriteMovie -> unFavourite(event.id)
        }
    }

    private fun loadMovie(id: Long) {
        getMovieDetailsUseCase.execute(GetMovieDetailsParam(id))
            .onEach { result ->
                when (result) {
                    is UseCaseResult.Failure -> _error.send(result.exception.toString())
                    is UseCaseResult.Success -> _viewState.update { it.copy(result.data.toUi()) }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun favourite(id: Long) {

    }

    private fun unFavourite(id: Long) {

    }

    data class ViewState(
        val movieDetails: MovieDetailsUiModel? = null,
        val isFavourite: Boolean = false,
        val loading: Boolean = false,
    )

    sealed interface ViewEvent {
        data class LoadMovie(val id: Long) : ViewEvent
        data class FavouriteMovie(val id: Long) : ViewEvent
        data class UnFavouriteMovie(val id: Long) : ViewEvent
    }
}
