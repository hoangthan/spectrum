package com.spectrum.libraries.persistence

import android.content.Context
import androidx.room.Room
import com.spectrum.libraries.persistence.dao.FavouriteMovieDao
import com.spectrum.libraries.persistence.dao.GenreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "SpectrumMovieDB"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME)
            .enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGenreDao(appDb: AppDatabase): GenreDao = appDb.genreDao()

    @Provides
    @Singleton
    fun provideFavMovieDao(appDb: AppDatabase): FavouriteMovieDao = appDb.favMovieDao()
}
