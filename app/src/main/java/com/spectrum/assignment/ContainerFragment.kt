package com.spectrum.assignment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spectrum.assignment.databinding.FragmentContainerBinding
import com.spectrum.features.core.utils.viewBinding

class ContainerFragment : Fragment(R.layout.fragment_container) {

    private lateinit var pagerAdapter: HomePagerAdapter
    private val binding by viewBinding(FragmentContainerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initViewListener()
    }

    private fun initViewListener() {
        binding.bottomNav.setOnItemSelectedListener(::onBottomNavItemSelected)
    }

    private fun initViewPager() {
        pagerAdapter = HomePagerAdapter(this)
        binding.vpHomeScreen.adapter = pagerAdapter
        binding.vpHomeScreen.isUserInputEnabled = false //Prevent swipe action
    }

    private fun onBottomNavItemSelected(menuItem: MenuItem): Boolean {
        val index = pagerAdapter.getIndex(menuItem.itemId) ?: return false
        binding.vpHomeScreen.setCurrentItem(index, false)
        return true
    }
}
