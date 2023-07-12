package com.spectrum.features.movie.ui.screens.movieList

import android.os.Parcelable
import com.spectrum.libraries.movie.domain.model.MovieSource
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MovieScreen : Parcelable {
    NowPlaying, TopRated, Popular, Upcoming;
}

fun MovieScreen.toMovieSource(): MovieSource {
    return when (this) {
        MovieScreen.NowPlaying -> MovieSource.NowPlaying
        MovieScreen.TopRated -> MovieSource.TopRated
        MovieScreen.Upcoming -> MovieSource.Upcoming
        MovieScreen.Popular -> MovieSource.Popular
    }
}