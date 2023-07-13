package com.spectrum.features.movie.ui.screens.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spectrum.features.movie.ui.components.MovieUiModel
import com.spectrum.features.movie.ui.components.toUiModel
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.model.PagedMovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class AbstractMoviePagingSource : PagingSource<Int, MovieUiModel>() {

    abstract suspend fun loadData(params: LoadParams<Int>): UseCaseResult<PagedMovieList>

    override fun getRefreshKey(state: PagingState<Int, MovieUiModel>): Int? {
        return state.anchorPosition?.let {
            val previous = state.closestPageToPosition(it)?.prevKey?.plus(1)
            val next = state.closestPageToPosition(it)?.nextKey?.minus(1)
            previous ?: next
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUiModel> {
        val pageNumber = params.key ?: 1
        val result = withContext(Dispatchers.IO) {
            loadData(params)
        }

        return when (result) {
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
