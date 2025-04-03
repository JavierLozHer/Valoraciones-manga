package edu.iesam.valoracionesmanga.features.manga.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MangaDao {

    @Query("SELECT * FROM $MANGA_TABLE")
    suspend fun findAll(): List<MangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg mangaEntity: MangaEntity)

    @Query("SELECT * FROM $MANGA_TABLE WHERE $MANGA_ID = :id")
    suspend fun findById(id: String): MangaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(manga: MangaEntity)

}