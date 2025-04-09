package edu.iesam.valoracionesmanga.features.assessment.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import edu.iesam.valoracionesmanga.features.assessment.data.local.converters.AssessmentConverters

const val ASSESSMENT_TABLE = "assessment"
const val ASSESSMENT_ID = "assessment_id"

@Entity(tableName = ASSESSMENT_TABLE)
@TypeConverters(AssessmentConverters::class)
class AssessmentEntity (
    @PrimaryKey @ColumnInfo(name = ASSESSMENT_ID) val id: String,
    @ColumnInfo(name = "comment") val comment: String,
    @ColumnInfo(name = "manga_id") val mangaId: String,
    @ColumnInfo(name = "score") val score: Number,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "saved_at") val savedAt: Long = System.currentTimeMillis()
)