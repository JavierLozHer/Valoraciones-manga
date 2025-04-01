package edu.iesam.valoracionesmanga.features.manga.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import edu.iesam.valoracionesmanga.features.manga.data.local.converters.MangaConverters
import edu.iesam.valoracionesmanga.features.manga.domain.Staff

const val MANGA_TABLE = "manga"
const val MANGA_ID = "manga_id"

@Entity(tableName = MANGA_TABLE)
@TypeConverters(MangaConverters::class)
class MangaEntity (
    @PrimaryKey @ColumnInfo(name = MANGA_ID) val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "synopsis") val synopsis: String,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "finish_date") val finishDate: String,
    @ColumnInfo(name = "genres") val genres: List<String>,
    @ColumnInfo(name = "staff") val staff: List<Staff>,
    @ColumnInfo(name = "saved_at") val savedAt: Long = System.currentTimeMillis()
)