package com.spectrum.features.movie.ui.screens.movieList

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.spectrum.feature.movie.R
import com.spectrum.feature.movie.databinding.FragmentMovieListBinding
import com.spectrum.features.core.utils.utils.parcelable
import com.spectrum.features.core.utils.viewBinding
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListFragment
import com.spectrum.features.movie.ui.screens.movieList.MovieListViewModel.ViewEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListFragment : AbstractMovieListFragment(R.layout.fragment_movie_list) {

    private val viewModel by viewModels<MovieListViewModel>()
    private val binding by viewBinding(FragmentMovieListBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieType: MovieScreen = requireArguments().parcelable(movieTypeKey)
        viewModel.dispatchEvent(ViewEvent.UpdateType(movieType))
    }

    override fun getRecyclerView() = binding.rcvMovieList

    override fun getMoviePagingFlow() = viewModel.getMoviePagingFlow()

    override fun getLoadingView() = binding.loadingView

    companion object {
        private const val movieTypeKey = "MovieType"
        fun newInstance(movieType: MovieScreen): MovieListFragment {
            return MovieListFragment().apply {
                arguments = bundleOf(movieTypeKey to movieType)
            }
        }
    }
}
