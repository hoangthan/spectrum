package com.spectrum.features.movie.ui.screens.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spectrum.features.core.R
import com.spectrum.features.core.utils.utils.DeeplinkHelper
import com.spectrum.features.core.utils.utils.collectWhenResumed
import com.spectrum.features.core.utils.utils.showMessage
import com.spectrum.features.movie.ui.components.MovieItemDecoration
import com.spectrum.features.movie.ui.components.MovieListAdapter
import com.spectrum.features.movie.ui.components.MovieUiModel
import kotlinx.coroutines.flow.Flow


abstract class AbstractMovieListFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private var _movieAdapter: MovieListAdapter? = null
    private val movieAdapter: MovieListAdapter
        get() {
            _movieAdapter ?: run {
                _movieAdapter = MovieListAdapter(::openMovieDetails)
            }
            return _movieAdapter!!
        }

    private val _loadingView: View?
        get() = getLoadingView()

    private val _emptyView: View?
        get() = getEmptyView()

    private val _recyclerView: RecyclerView
        get() = getRecyclerView()

    protected open fun getLoadingView(): View? = null

    protected open fun getEmptyView(): View? = null

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getMoviePagingFlow(): Flow<PagingData<MovieUiModel>>

    protected open fun shouldShowEmptyView(loadState: CombinedLoadStates): Boolean {
        return loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                movieAdapter.itemCount < 1
    }

    protected open fun onLoadStateUpdated(loadState: CombinedLoadStates) {
        when (val refresh = loadState.refresh) {
            is LoadState.Loading -> _loadingView?.isVisible = true
            is LoadState.NotLoading -> _loadingView?.isGone = true
            is LoadState.Error -> {
                showMessage(refresh.error.message)
                _loadingView?.isGone = true
            }
        }

        _emptyView?.isVisible = shouldShowEmptyView(loadState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _movieAdapter = null
    }

    private fun initRecyclerView() {
        val margin = R.dimen.margin_regular
        val layoutManager = LinearLayoutManager(requireContext())
        val marginDimen = resources.getDimensionPixelSize(margin)

        with(_recyclerView) {
            this.setHasFixedSize(true)
            this.adapter = movieAdapter
            this.layoutManager = layoutManager
            this.addItemDecoration(MovieItemDecoration(marginDimen))
        }
    }

    private fun collectData() {
        getMoviePagingFlow().collectWhenResumed(viewLifecycleOwner) {
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        movieAdapter.loadStateFlow.collectWhenResumed(viewLifecycleOwner, ::onLoadStateUpdated)
    }

    private fun openMovieDetails(movie: MovieUiModel) {
        val movieDetailsUriTemplate = getString(R.string.movie_details_url)
        val movieDetailsUri = DeeplinkHelper.toUri(movieDetailsUriTemplate, movie.id)
        val request = NavDeepLinkRequest.Builder.fromUri(movieDetailsUri).build()
        val navOptions: NavOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .setRestoreState(true)
            .build()

        findNavController().navigate(request, navOptions)
    }
}
