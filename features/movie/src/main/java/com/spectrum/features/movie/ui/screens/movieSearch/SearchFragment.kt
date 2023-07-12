package com.spectrum.features.movie.ui.screens.movieSearch

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.spectrum.feature.movie.R
import com.spectrum.feature.movie.databinding.FragmentSearchMovieBinding
import com.spectrum.features.core.utils.viewBinding
import com.spectrum.features.movie.ui.screens.common.AbstractMovieListFragment
import com.spectrum.features.movie.ui.screens.movieSearch.SearchMovieViewModel.ViewEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : AbstractMovieListFragment(R.layout.fragment_search_movie) {

    private val viewModel by viewModels<SearchMovieViewModel>()
    private val binding by viewBinding(FragmentSearchMovieBinding::bind)

    override fun getRecyclerView() = binding.rcvMovieList

    override fun getMoviePagingFlow() = viewModel.getMoviePagingFlow()

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.edtSearch.doAfterTextChanged {
            viewModel.dispatchEvent(ViewEvent.UpdateSearchName(it.toString()))
        }
    }
}
