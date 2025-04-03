package edu.iesam.valoracionesmanga.features.genres.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val GENRE_TABLE = "genre"

@Entity(tableName = GENRE_TABLE)
class GenreEntity (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "saved_at") val savedAt: Long = System.currentTimeMillis()
)