package com.spectrum.assignment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.spectrum.assignment.databinding.FragmentHomeBinding
import com.spectrum.features.movie.ui.screens.movieList.TabAdapterFragment
import com.spectrum.features.core.utils.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val pagerAdapter = TabAdapterFragment(this)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, index ->
            tab.setText(pagerAdapter.getScreenName(index))
        }.attach()
    }
}
