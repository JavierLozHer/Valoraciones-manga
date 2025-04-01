package edu.iesam.valoracionesmanga.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.iesam.valoracionesmanga.BuildConfig
import edu.iesam.valoracionesmanga.features.manga.data.local.MangaDao
import edu.iesam.valoracionesmanga.features.manga.data.local.MangaEntity

@Database(
    entities = [MangaEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class ValoracionesMangaDataBase: RoomDatabase() {
    abstract fun mangaDao(): MangaDao
}