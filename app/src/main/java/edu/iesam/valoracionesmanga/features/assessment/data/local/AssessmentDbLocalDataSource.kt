package edu.iesam.valoracionesmanga.features.assessment.data.local

import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import org.koin.core.annotation.Single

@Single
class AssessmentDbLocalDataSource(private val assessmentDao: AssessmentDao) {

    private val expireTime : Long = 6000

    suspend fun saveAll(assessment: List<Assessment>) {
        val assessmentEntity = assessment.map { it.toEntity() }

        assessmentDao.saveAll(*assessmentEntity.toTypedArray())
    }

    suspend fun getAll(): Result<List<Assessment>> {
        val assessmentEntity = assessmentDao.findAll()

        if(assessmentEntity.isEmpty()) {
            return Result.failure(ErrorApp.DataErrorApp)
        }
        return if (System.currentTimeMillis() - assessmentEntity[0].savedAt < expireTime) {
            Result.success(assessmentEntity.map { it.toModel() })
        } else {
            Result.failure(ErrorApp.DataErrorApp)
        }
    }
}