package com.spectrum.features.movie.ui.screens.favourite

import androidx.paging.PagingData
import com.spectrum.features.movie.ui.components.MovieUiModel
import com.spectrum.features.movie.ui.components.toUiModel
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListViewModel
import com.spectrum.libraries.core.usecase.UseCaseResult.Success
import com.spectrum.libraries.movie.domain.model.Movie
import com.spectrum.libraries.movie.domain.usecase.GetFavouriteMovieUseCase
import com.spectrum.libraries.movie.domain.usecase.GetFavouriteMovieUseCase.QueryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@HiltViewModel
class FavouriteMovieViewModel @Inject constructor(
    private val getFavMovieUseCase: GetFavouriteMovieUseCase
) : AbstractMovieListViewModel() {

    override fun getMoviePagingFlow(): Flow<PagingData<MovieUiModel>> {
        return getFavMovieUseCase.execute(QueryType.All)
            .mapNotNull { result ->
                val movies = result as? Success<List<Movie>>
                movies?.data?.mapNotNull { it.toUiModel() }
            }
            .map { PagingData.from(it) }
    }
}
