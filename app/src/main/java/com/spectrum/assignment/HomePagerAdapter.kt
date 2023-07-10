package com.spectrum.assignment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val screens = listOf(
        Screen(HomeFragment(), 0, R.id.actionHome),
        Screen(SearchFragment(), 1, R.id.actionSearch),
        Screen(FavouriteFragment(), 2, R.id.actionFavourite),
    )

    fun getIndex(@IdRes actionId: Int) = screens.firstOrNull { it.actionId == actionId }?.index

    override fun getItemCount(): Int = screens.size

    override fun createFragment(position: Int): Fragment = screens[position].fragment

    private data class Screen(
        val fragment: Fragment,
        val index: Int,
        @IdRes val actionId: Int,
    )
}
