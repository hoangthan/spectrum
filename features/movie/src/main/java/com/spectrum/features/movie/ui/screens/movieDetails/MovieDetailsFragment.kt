package com.spectrum.features.movie.ui.screens.movieDetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.spectrum.feature.movie.R
import com.spectrum.feature.movie.databinding.FragmentMovieDetailsBinding
import com.spectrum.features.core.utils.utils.collectWhenStarted
import com.spectrum.features.core.utils.viewBinding
import com.spectrum.features.movie.ui.components.MovieDetailsUiModel
import com.spectrum.features.movie.ui.screens.movieDetails.MovieDetailsViewModel.ViewEvent
import com.spectrum.features.movie.utils.ImageSize
import com.spectrum.features.movie.utils.ImageSourceSelector
import dagger.hilt.android.AndroidEntryPoint
import com.spectrum.features.core.R as coreR

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val binding by viewBinding(FragmentMovieDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = requireArguments().getLong(MOVIE_ID_KEY)
        viewModel.dispatchEvent(ViewEvent.LoadMovie(movieId))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewListener()
        initDataCollector()
    }

    private fun initDataCollector() {
        viewModel.viewState.collectWhenStarted(viewLifecycleOwner) {
            bindMovieDetails(it.movieDetails)
        }
    }

    private fun initViewListener() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun bindMovieDetails(details: MovieDetailsUiModel?) {
        details ?: return
        with(binding) {
            val imageUrl = ImageSourceSelector.getImageUrl(details.backdropPath, ImageSize.Original)
            imgBackdrop.load(imageUrl) { placeholder(coreR.drawable.ic_loading) }
            tvMovieName.text = details.title
            tvOverView.text = details.overview
            tvTagLine.text = details.tagline
            tvTagLine.isGone = details.tagline.isNullOrBlank()
            tvReleaseDate.text = details.releaseDate
            tvVoteCount.text = "${details.voteAverage}/${details.voteCount} votes"
            addChipToGroup(binding.chipGroupGenres, details.genres.map { it.name })
            addChipToGroup(binding.chipSpokenLanguage, details.spokenLanguages)
        }
    }

    private fun addChipToGroup(group: ChipGroup, chipsContent: List<String>) {
        group.removeAllViews()
        chipsContent.forEach {
            val chip = Chip(binding.root.context, null, R.style.ChipGenresStyle)
            chip.setEnsureMinTouchTargetSize(false)
            chip.text = it
            group.addView(chip)
        }
    }

    companion object {
        const val MOVIE_ID_KEY = "id"
    }
}
