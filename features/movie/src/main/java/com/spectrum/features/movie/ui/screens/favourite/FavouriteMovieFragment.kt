package com.spectrum.features.movie.ui.screens.favourite

import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.spectrum.feature.movie.R
import com.spectrum.feature.movie.databinding.FragmentMovieListBinding
import com.spectrum.features.core.utils.viewBinding
import com.spectrum.features.movie.ui.components.MovieUiModel
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class FavouriteMovieFragment : AbstractMovieListFragment(R.layout.fragment_movie_list) {

    private val viewModel: FavouriteMovieViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieListBinding::bind)

    override fun getRecyclerView(): RecyclerView = binding.rcvMovieList

    override fun getMoviePagingFlow(): Flow<PagingData<MovieUiModel>> {
        return viewModel.getMoviePagingFlow()
    }
}
