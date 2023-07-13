package com.spectrum.libraries.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spectrum.libraries.persistence.entity.FavouriteMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouriteMovieDao {

    @Query("SELECT * FROM favourite_movie")
    fun getAll(): Flow<List<FavouriteMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg movie: FavouriteMovieEntity)

    @Query("DELETE FROM favourite_movie WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM favourite_movie WHERE id IN (:id)")
    fun loadByIds(id: LongArray): Flow<List<FavouriteMovieEntity>>
}
