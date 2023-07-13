package com.spectrum.libraries.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spectrum.libraries.persistence.converter.IntListConverter

@Entity(tableName = "favourite_movie")
@TypeConverters(IntListConverter::class)
data class FavouriteMovieEntity(
    @PrimaryKey
    val id: Long,
    val adult: Boolean?,
    val backdropPath: String?,
    val genresIds: List<Int>,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)
