package com.spectrum.libraries.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spectrum.libraries.persistence.entity.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre")
    suspend fun getAll(): List<GenreEntity>

    @Insert
    suspend fun insert(vararg genres: GenreEntity)
}
