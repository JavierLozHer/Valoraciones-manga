package edu.iesam.valoracionesmanga.features.genres.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenreDao {

    @Query("SELECT * FROM $GENRE_TABLE")
    suspend fun findAll(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg genreEntity: GenreEntity)

}