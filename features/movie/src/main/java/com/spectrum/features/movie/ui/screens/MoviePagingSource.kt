package com.spectrum.features.movie.ui.screens

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spectrum.features.movie.ui.components.MovieUiModel
import com.spectrum.features.movie.ui.components.toUiModel
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.usecase.GetMovieUseCase
import com.spectrum.libraries.movie.domain.usecase.GetMovieUseCase.GetMovieParam
import com.spectrum.libraries.movie.domain.usecase.model.MovieSource
import kotlinx.coroutines.flow.first

class MoviePagingSource constructor(
    private val movieSource: MovieSource,
    private val getMovieUseCase: GetMovieUseCase,
) : PagingSource<Int, MovieUiModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieUiModel>): Int? {
        return state.anchorPosition?.let {
            val previous = state.closestPageToPosition(it)?.prevKey?.plus(1)
            val next = state.closestPageToPosition(it)?.nextKey?.minus(1)
            previous ?: next
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUiModel> {
        val pageNumber = params.key ?: 1
        val useCaseParam = GetMovieParam(movieSource, pageNumber)

        return when (val result = getMovieUseCase.execute(useCaseParam).first()) {
            is UseCaseResult.Failure -> {
                LoadResult.Error(result.exception)
            }

            is UseCaseResult.Success -> {
                val movies = result.data.movies.mapNotNull { it.toUiModel() }
                val prevKey = if (pageNumber == 1) null else pageNumber - 1
                val nextKey = if (pageNumber <= result.data.totalPages) pageNumber + 1 else null
                LoadResult.Page(data = movies, prevKey = prevKey, nextKey = nextKey)
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
