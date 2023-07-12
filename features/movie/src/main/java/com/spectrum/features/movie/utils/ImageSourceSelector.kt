package com.spectrum.features.movie.utils

enum class PosterSize(val path: String) {
    Width92("w92"),
    Width154("w154"),
    Width185("w185"),
    Width342("w342"),
    Width500("w500"),
    Width780("w780"),
    Original("original")
}

object ImageSourceSelector {

    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

    fun getImageUrl(path: String?, size: PosterSize): String? {
        path ?: return null
        return "$IMAGE_BASE_URL/${size.path}$path"
    }
}
