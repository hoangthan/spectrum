package com.spectrum.libraries.movie.domain.model

sealed interface MovieSource {
    object NowPlaying : MovieSource
    object Popular : MovieSource
    object TopRated : MovieSource
    object Upcoming : MovieSource
}
