package com.spectrum.assignment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spectrum.assignment.databinding.FragmentContainerBinding
import com.spectrum.features.core.utils.viewBinding

class ContainerFragment : Fragment(R.layout.fragment_container) {

    private val binding by viewBinding(FragmentContainerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpHomeScreen.isUserInputEnabled = false //Prevent swipe action

        binding.bottomNav.setOnItemSelectedListener {
            onBottomNavItemSelected(it)
            it.itemId != R.id.actionSearch //Should not high-light the color of search icon
        }
    }

    private fun onBottomNavItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.actionSearch -> {
                findNavController().navigate(R.id.action_containerFragment_to_favouriteFragment2)
            }

            R.id.actionHome -> {

            }

            R.id.actionFavourite -> {

            }
        }
    }
}
