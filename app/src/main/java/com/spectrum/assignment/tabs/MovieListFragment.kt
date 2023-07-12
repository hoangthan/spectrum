package com.spectrum.assignment.tabs

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.spectrum.assignment.R
import com.spectrum.assignment.components.MovieItemDecoration
import com.spectrum.assignment.components.MovieListAdapter
import com.spectrum.assignment.databinding.FragmentMovieListBinding
import com.spectrum.assignment.tabs.MovieListViewModel.ViewEvent
import com.spectrum.features.core.utils.utils.collectWhenCreated
import com.spectrum.features.core.utils.utils.parcelable
import com.spectrum.features.core.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var movieType: MovieScreen
    private val movieAdapter = MovieListAdapter()
    private val viewModel by viewModels<MovieListViewModel>()
    private val binding by viewBinding(FragmentMovieListBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieType = requireArguments().parcelable(movieTypeKey)
        viewModel.dispatchEvent(ViewEvent.UpdateType(movieType))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectData()
    }

    private fun initRecyclerView() {
        val margin = com.spectrum.features.core.R.dimen.margin_regular
        val layoutManager = LinearLayoutManager(requireContext())
        val marginDimen = resources.getDimensionPixelSize(margin)

        with(binding.rcvMovieList) {
            this.setHasFixedSize(true)
            this.adapter = movieAdapter
            this.layoutManager = layoutManager
            this.addItemDecoration(MovieItemDecoration(marginDimen))
        }
    }

    private fun collectData() {
        viewModel.moviePaging.collectWhenCreated(viewLifecycleOwner) {
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    companion object {
        private const val movieTypeKey = "MovieType"
        fun newInstance(movieType: MovieScreen): MovieListFragment {
            return MovieListFragment().apply {
                arguments = bundleOf(movieTypeKey to movieType)
            }
        }
    }
}
