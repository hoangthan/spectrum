package com.spectrum.features.movie.ui.screens.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spectrum.features.core.utils.utils.collectWhenCreated
import com.spectrum.features.movie.ui.components.MovieItemDecoration
import com.spectrum.features.movie.ui.components.MovieListAdapter
import com.spectrum.features.movie.ui.components.MovieUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow


abstract class AbstractMovieListFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private val movieAdapter = MovieListAdapter()

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getMoviePagingFlow(): Flow<PagingData<MovieUiModel>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectData()
    }

    private fun initRecyclerView() {
        val margin = com.spectrum.features.core.R.dimen.margin_regular
        val layoutManager = LinearLayoutManager(requireContext())
        val marginDimen = resources.getDimensionPixelSize(margin)

        with(getRecyclerView()) {
            this.setHasFixedSize(true)
            this.adapter = movieAdapter
            this.layoutManager = layoutManager
            this.addItemDecoration(MovieItemDecoration(marginDimen))
        }
    }

    private fun collectData() {
        getMoviePagingFlow().collectWhenCreated(viewLifecycleOwner) {
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
