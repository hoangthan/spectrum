package com.spectrum.assignment.tabs

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.spectrum.assignment.tabs.MovieListViewModel.MovieType
import com.spectrum.features.core.R

class TabAdapterFragment(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val screens = listOf(
        Screen(MovieListFragment.newInstance(MovieType.NowPlaying), 0, R.string.now_playing),
        Screen(MovieListFragment.newInstance(MovieType.Popular), 1, R.string.popular),
        Screen(MovieListFragment.newInstance(MovieType.TopRated), 2, R.string.top_rated),
        Screen(MovieListFragment.newInstance(MovieType.Upcoming), 3, R.string.upcoming),
    )

    override fun getItemCount(): Int = screens.size

    override fun createFragment(position: Int): Fragment = screens[position].fragment

    fun getScreenName(index: Int): Int = screens[index].titleRes

    private data class Screen(
        val fragment: Fragment,
        val index: Int,
        @StringRes val titleRes: Int,
    )
}
