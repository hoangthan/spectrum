package com.spectrum.features.movie.ui.screens.favourite

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.spectrum.feature.movie.R
import com.spectrum.feature.movie.databinding.FragmentMovieListBinding
import com.spectrum.features.core.utils.viewBinding
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMovieFragment : AbstractMovieListFragment(R.layout.fragment_movie_list) {

    private val viewModel: FavouriteMovieViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieListBinding::bind)

    override fun getLoadingView() = binding.loadingView

    override fun getRecyclerView(): RecyclerView = binding.rcvMovieList

    override fun getEmptyView() = binding.emptyView

    override fun getMoviePagingFlow() = viewModel.getMoviePagingFlow()
}
