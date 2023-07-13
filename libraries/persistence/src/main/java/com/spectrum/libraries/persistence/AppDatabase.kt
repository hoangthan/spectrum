package com.spectrum.libraries.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spectrum.libraries.persistence.dao.FavouriteMovieDao
import com.spectrum.libraries.persistence.dao.GenreDao
import com.spectrum.libraries.persistence.entity.FavouriteMovieEntity
import com.spectrum.libraries.persistence.entity.GenreEntity

@Database(entities = [GenreEntity::class, FavouriteMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun favMovieDao(): FavouriteMovieDao
}
