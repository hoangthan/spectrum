package com.spectrum.features.movie.ui.screens.common

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.spectrum.features.movie.ui.components.MovieUiModel
import kotlinx.coroutines.flow.Flow

abstract class AbstractMovieListViewModel : ViewModel() {

    protected val pageConfig = PagingConfig(
        prefetchDistance = 1,
        pageSize = AbstractMoviePagingSource.PAGE_SIZE,
        initialLoadSize = AbstractMoviePagingSource.PAGE_SIZE,
    )

    abstract fun getMoviePagingFlow(): Flow<PagingData<MovieUiModel>>
}
