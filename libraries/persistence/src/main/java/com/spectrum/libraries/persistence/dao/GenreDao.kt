package com.spectrum.libraries.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spectrum.libraries.persistence.entity.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre")
    fun getAll(): List<GenreEntity>

    @Insert
    fun insert(vararg users: GenreEntity)

    @Query("SELECT * FROM genre WHERE id IN (:id)")
    fun loadAllByIds(id: IntArray): List<GenreEntity>
}
