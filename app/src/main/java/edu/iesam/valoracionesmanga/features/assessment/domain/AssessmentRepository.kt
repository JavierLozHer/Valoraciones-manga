package edu.iesam.valoracionesmanga.features.assessment.domain

interface AssessmentRepository {
    suspend fun get(): Result<List<Assessment>>
    suspend fun save(assessment: Assessment): Result<Unit>
    suspend fun create(assessment: Assessment): Result<Unit>
}