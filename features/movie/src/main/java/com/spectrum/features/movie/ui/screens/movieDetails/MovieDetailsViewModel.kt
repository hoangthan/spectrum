package com.spectrum.features.movie.ui.screens.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spectrum.features.movie.ui.components.MovieDetailsUiModel
import com.spectrum.features.movie.ui.components.toUi
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.model.Movie
import com.spectrum.libraries.movie.domain.model.MovieDetails
import com.spectrum.libraries.movie.domain.usecase.GetFavouriteMovieUseCase
import com.spectrum.libraries.movie.domain.usecase.GetFavouriteMovieUseCase.QueryType
import com.spectrum.libraries.movie.domain.usecase.GetMovieDetailsUseCase
import com.spectrum.libraries.movie.domain.usecase.GetMovieDetailsUseCase.GetMovieDetailsParam
import com.spectrum.libraries.movie.domain.usecase.RemoveFavouriteMovieUseCase
import com.spectrum.libraries.movie.domain.usecase.SaveFavouriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val removeFavMovie: RemoveFavouriteMovieUseCase,
    private val saveFavMovie: SaveFavouriteMovieUseCase,
    private val getFavMovie: GetFavouriteMovieUseCase,
) : ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    private var movieDetails: MovieDetailsUiModel? = null
    private val movieIdState = MutableStateFlow<Long>(0)

    val movieDetailsState = movieIdState
        .onEach { _loadingState.update { true } }
        .flatMapLatest { getMovieDetails.execute(GetMovieDetailsParam(it)) }
        .onEach { _loadingState.update { false } }
        .onEach { if (it is UseCaseResult.Failure) _error.send(it.exception.toString()) }
        .mapNotNull { result -> result as? UseCaseResult.Success<MovieDetails> }
        .map { it.data.toUi() }
        .onEach { movieDetails = it }

    val favState = movieIdState
        .flatMapLatest { getFavMovie.execute(QueryType.Specific(it)) }
        .map {
            val result = it as? UseCaseResult.Success<List<Movie>>
            result?.data?.isNotEmpty() ?: false
        }

    fun dispatchEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.LoadMovie -> movieIdState.update { event.id }
            is ViewEvent.UpdateFavourite -> updateFavourite(event.isFavourite)
        }
    }

    private fun updateFavourite(favourite: Boolean) {
        viewModelScope.launch {
            val movie = createMovie(movieDetails ?: return@launch)
            if (favourite) saveFavMovie.execute(movie)
            else removeFavMovie.execute(movie)
        }
    }

    private fun createMovie(details: MovieDetailsUiModel): Movie {
        return Movie(
            id = details.id,
            adult = details.adult,
            backdropPath = details.backdropPath,
            genres = details.genres,
            genresIds = details.genres.map { it.id },
            originalLanguage = details.originalLanguage,
            originalTitle = details.originalTitle,
            overview = details.overview,
            popularity = details.popularity,
            posterPath = details.posterPath,
            releaseDate = details.releaseDate,
            title = details.title,
            video = details.video,
            voteAverage = details.voteAverage,
            voteCount = details.voteCount,
        )
    }

    sealed interface ViewEvent {
        data class LoadMovie(val id: Long) : ViewEvent
        data class UpdateFavourite(val isFavourite: Boolean) : ViewEvent
    }
}
