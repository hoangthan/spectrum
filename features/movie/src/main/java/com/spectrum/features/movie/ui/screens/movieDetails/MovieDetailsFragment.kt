package com.spectrum.features.movie.ui.screens.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.spectrum.feature.movie.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        const val MOVIE_ID_KEY = "id"
    }
}
