package edu.iesam.valoracionesmanga.features.genres.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import edu.iesam.valoracionesmanga.features.manga.data.local.converters.MangaConverters

const val GENRE_TABLE = "genre"

@Entity(tableName = GENRE_TABLE)
@TypeConverters(MangaConverters::class)
class GenreEntity (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "saved_at") val savedAt: Long = System.currentTimeMillis()
)