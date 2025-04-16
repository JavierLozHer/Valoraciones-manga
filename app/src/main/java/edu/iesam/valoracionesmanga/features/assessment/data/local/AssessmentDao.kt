package edu.iesam.valoracionesmanga.features.assessment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AssessmentDao {

    @Query("SELECT * FROM $ASSESSMENT_TABLE")
    suspend fun findAll(): List<AssessmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg assessmentEntity: AssessmentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(assessmentEntity: AssessmentEntity)
}